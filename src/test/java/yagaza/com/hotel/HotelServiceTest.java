package yagaza.com.hotel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import yagaza.com.order.OrderService;
import yagaza.com.order.SiteOrder;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserService;


import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HotelServiceTest {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private HotelRoomService hotelRoomService;
    @Autowired
    private HotelRoomRepository hotelRoomRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @Test
    public void hotelNumberCrawling(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String region = "부산";
        String url = "https://www.yanolja.com/search/" + region + "?pageKey=1731649938781&switchFilter=availableOnly%3Dtrue&filter=reservationTypeCodes%3DRESERVATION_TYPE_STAY";
        driver.get(url);
        System.out.println("성공");
        try {
            Thread.sleep(1500);
            WebElement hotels = driver.findElement(By.className("common_clearfix__M6urU"));
            var stTime = new Date().getTime();
            while (new Date().getTime() < stTime + 40000) { //30초 동안 무한스크롤 지속
                Thread.sleep(500); //리소스 초과 방지
                ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)", hotels);
            }

            List<WebElement> hotelElements = driver.findElements(By.className("common_clearfix__M6urU"));
            for (WebElement hotelElement : hotelElements){
                // title 추출
                String title = hotelElement.findElement(By.cssSelector("strong.PlaceListTitle_text__2511B")).getText();

                // 이미지 URL 추출
                String imgUrl = hotelElement.findElement(By.cssSelector("div.PlaceListImage_imageText__2XEMn"))
                        .getAttribute("style")
                        .replace("background-image: url(\"", "")
                        .replace("\");", "");

                // type 추출
                String type = hotelElement.findElement(By.cssSelector("div.PlaceListGrade_container__1oIhJ")).getText();

                // checkInTime 추출
                String checkInTime = hotelElement.findElements(By.cssSelector("span"))
                        .stream()
                        .filter(span -> span.getText().matches("\\d{2}:\\d{2}~"))
                        .findFirst()
                        .orElseThrow(() -> new Exception("Check-in time not found"))
                        .getText();

                // priceTwoPerson 추출
                String priceTwoPersonText = hotelElement.findElement(By.cssSelector("span.PlacePriceInfoV2_discountPrice__1PuwK"))
                        .getText()
                        .replace(",", "")
                        .replace("원", "");
                Integer priceTwoPerson = Integer.parseInt(priceTwoPersonText);

                // 결과 출력
                System.out.println("Title: " + title);
                System.out.println("Image URL: " + imgUrl);
                System.out.println("Type: " + type);
                System.out.println("Check-In Time: " + checkInTime);
                System.out.println("Price for Two: " + priceTwoPerson);

                hotelService.createHrefName(hrefToId(hotelElement.getAttribute("href")), hotelElement.getAttribute("href"),
                        hotelElement.getAttribute("title"));

            }
        }catch (Exception e){
            System.out.println("오류발생");
            e.printStackTrace();
        }
    }

    @Test
    public void 크롤링_유닛_테스트(){
        List<Hotel> hotelList = hotelRepository.findAll();
        long placeId = 0l;
        for(int i = 0 ; i < hotelList.size(); i++){
            placeId = hotelList.get(i).getId();
            hotelCrawling(placeId);
        }
    }
    @Test
    public void 크롤링_단일_테스트(){
        hotelCrawling(10041088);
    }
    public void hotelCrawling(long placeId) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 요소 로드를 위한 대기 시간 설정
        String url = "https://place-site.yanolja.com/places/" + placeId + "?checkInDate=2024-11-21&checkOutDate=2024-11-22&adultPax=";
        driver.get(url);

        try {
            driver.get(url + 1);
            Thread.sleep(1500);

            // 주소, 이미지, 호텔 정보 크롤링
            WebElement addressElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".address .css-9ih6hc span")));
            String address = addressElement.getText();

            String imgElement = driver.findElement(By.className("css-sr2c7j")).getAttribute("src");
            String hotelContent = driver.findElement(By.className("css-1muque2")).getText();

            this.hotelService.createRegionImgContent(placeId, address, imgElement, hotelContent);
            Hotel hotel = this.hotelService.getHotel(placeId);

            for (int i = 1; i <= 5; i++) {
                driver.get(url + i); // 각 인원 페이지 로드

                // 선택한 인원 정보로 객실 없음 메시지 확인
                List<WebElement> noRoomsMessages = driver.findElements(By.className("css-n990ss"));
                if (!noRoomsMessages.isEmpty() && noRoomsMessages.get(0).getText().equals("선택하신 인원정보로 이용가능한 객실이 없습니다.")) {
                    continue;
                }

                // rate-plan-container 클래스에 해당하는 요소들 가져오기
                List<WebElement> ratePlanElements = driver.findElements(By.className("rate-plan-container"));
                for (WebElement element : ratePlanElements) {
                    String priceText = element.findElement(By.className("css-13bialb")).getText().replaceAll("[^0-9]", "");
                    int parsedPrice = Integer.parseInt(priceText); // 숫자만 추출

                    // 인원수에 따라 가격 설정
                    switch (i) {
                        case 1 -> hotel.setPriceOnePerson(parsedPrice);
                        case 2 -> hotel.setPriceTwoPerson(parsedPrice);
                        case 3 -> hotel.setPriceThreePerson(parsedPrice);
                        case 4 -> hotel.setPriceFourPerson(parsedPrice);
                        case 5 -> hotel.setPriceFivePerson(parsedPrice);
                    }
                    this.hotelRepository.save(hotel);
                    System.out.println("세이브 완료");
                    break;
                }
            }
            System.out.println("성공");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("오류발생");
        } finally {
            driver.quit();
        }
    }

    public long hrefToId(String href){
        long id = Long.parseLong(href.replaceAll("[^0-9]", ""));
        return id;
    }
    public boolean isLodgment(Element element){
        if(element.getElementsByClass("css-1vtgvgb").text().contains("숙박")){
            return true;
        }
        return false;
    }

    //호텔의 타입을 크롤링
    @Test
    public void setHotelType(){
        List<Hotel> hotelList = hotelRepository.findAll();
        for (Hotel hotel : hotelList){
            System.out.println(hotel.getHotelName());
            if (hotel.getHotelHref().contains("motel")){
                hotel.setType("모텔");
            } else if (hotel.getHotelHref().contains("hotel")) {
                hotel.setType("호텔/리조트");
            } else if (hotel.getHotelHref().contains("pension")) {
                hotel.setType("펜션/풀빌라");
            }else {
                hotel.setType("게스트 하우스");
            }
            this.hotelRepository.save(hotel);
        }
    }
}