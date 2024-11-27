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


    @Test
    public void hotelNumberCrawling(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String region = "부산";
//        인원수 수동으로 설정 해야함
        int prod = 5;

        String url = "https://www.yanolja.com/search/" + region + "?pageKey=1731649938781&switchFilter=availableOnly%3Dtrue&filter=reservationTypeCodes%3DRESERVATION_TYPE_STAY";
        driver.get(url);
        System.out.println("성공");
        try {
            Thread.sleep(1500);
            WebElement hotels = driver.findElement(By.className("common_clearfix__M6urU"));
            var stTime = new Date().getTime();
            while (new Date().getTime() < stTime + 60000) { //30초 동안 무한스크롤 지속
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
                        .orElseThrow(() -> new Exception(""))
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

                if (hotelService.getHotelByHotelName(title).isPresent()){
                    Hotel hotel = hotelService.getHotelByHotelName(title).get();
                    hotelService.setHotelPrice(hotel, prod, priceTwoPerson);

                    continue;
                }
                hotelService.createHotel(title, checkInTime, imgUrl, type, prod, priceTwoPerson, region);
            }
        }catch (Exception e){
            System.out.println("오류발생");
            e.printStackTrace();
        }
    }
    //호텔의 타입을 크롤링

}