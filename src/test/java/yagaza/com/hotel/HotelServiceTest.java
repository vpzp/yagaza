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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import yagaza.com.order.OrderService;
import yagaza.com.order.SiteOrder;
import yagaza.com.user.SiteUser;
import yagaza.com.user.UserService;


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
        String url = "https://www.yanolja.com/search/" + "부산";
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
    public void hotelCrawling(long placeId){
        String url = "https://place-site.yanolja.com/places/" + placeId + "?checkInDate=2024-05-25&checkOutDate=2024-05-26"+"&adultPax=";
        String name, href, checkInTime, price, hotelContent,hotelRoomContent, id;
        try{
            Document document = Jsoup.connect(url + 1).get();

            String adress = document.getElementsByClass("address css-18ufud2").text();
            String imgElement = document.getElementsByClass("css-sr2c7j").attr("src");
            hotelContent = document.getElementsByClass("css-1muque2").text();
            this.hotelService.createRegionImgContent(placeId, adress, imgElement ,hotelContent);
            Hotel hotel = this.hotelService.getHotel(placeId);
            Elements elements = document.getElementsByClass("rate-plan-container");
            for (int i = 1 ; i <= 5 ; i++){
                document = Jsoup.connect(url + i).get();
//                System.out.println(url + i);
//                System.out.println(document.getElementsByClass("css-n990ss").text());
                if(document.getElementsByClass("css-n990ss").text().equals("선택하신 인원정보로 이용가능한 객실이 없습니다.")){
                    continue;
                }
                elements = document.getElementsByClass("rate-plan-container");
                for (int j = 0 ; j < elements.size() ; j++){
//                    System.out.println(elements.get(j).getElementsByClass("css-1vtgvgb").text());
                    if (isLodgment(elements.get(j))){
                        switch (i){
                            case 1 : hotel.setPriceOnePerson(Integer.parseInt((elements.get(j).getElementsByClass("css-13bialb").text()).replaceAll("[^0-9]", "")));
                                System.out.println(Integer.parseInt((elements.get(j).getElementsByClass("css-13bialb").text()).replaceAll("[^0-9]", "")));
                                break;
                            case 2 : hotel.setPriceTwoPerson(Integer.parseInt((elements.get(j).getElementsByClass("css-13bialb").text()).replaceAll("[^0-9]", "")));
                                System.out.println(elements.get(j).getElementsByClass("css-13bialb").text().replaceAll("[^0-9]", ""));
                                break;
                            case 3: hotel.setPriceThreePerson(Integer.parseInt((elements.get(j).getElementsByClass("css-13bialb").text()).replaceAll("[^0-9]", "")));
                                System.out.println(elements.get(j).getElementsByClass("css-13bialb").text().replaceAll("[^0-9]", ""));
                                break;
                            case 4: hotel.setPriceFourPerson(Integer.parseInt((elements.get(j).getElementsByClass("css-13bialb").text()).replaceAll("[^0-9]", "")));
                                System.out.println(elements.get(j).getElementsByClass("css-13bialb").text().replaceAll("[^0-9]", ""));
                                break;
                            case 5: hotel.setPriceFivePerson(Integer.parseInt((elements.get(j).getElementsByClass("css-13bialb").text()).replaceAll("[^0-9]", "")));
                                System.out.println(elements.get(j).getElementsByClass("css-13bialb").text().replaceAll("[^0-9]", ""));
                                break;
                        }
                        this.hotelRepository.save(hotel);
                        System.out.println("세이브");
                        break;
                    }
                }

            }
            System.out.println("성공");

//            Elements hotelRoomElements = document.getElementsByClass("css-1nnj57j");
//            for(Element element : hotelRoomElements){
//                //숙박가능한 객실만 체크하는 조건문
//                //TODO 호텔룸의 이미지 주소값 받아오기, 호텔룸 테이블 생성하는 코드 작성
//                if(isLodgment(element)){
//                    System.out.println("객실 이름은 : "+ element.getElementsByClass("css-1rr4h0w").text());
//                    System.out.println("href 주소는 : " + element.getElementsByClass("css-1w7jlh2").attr("href"));
//                    System.out.println("체크인 시간은 : " + element.getElementsByClass("css-1qxtkjb").text());
//                    System.out.println("가격은 : "+ element.getElementsByClass("price").text());
//                    System.out.println();
//                    name = element.getElementsByClass("css-1rr4h0w").text();
//                    href = element.getElementsByClass("css-1w7jlh2").attr("href");
//                    checkInTime = element.getElementsByClass("css-1qxtkjb").text();
//                    price = element.getElementsByClass("price").text();
//                    hotelRoomContent = element.getElementsByClass("css-1w8cj78").text();
//                    id = href.replaceAll("/places/" + placeId +"/","");
//
//                    hotelRoomService.create(id, name, href, checkInTime, price, hotelRoomContent, placeId);
//                }
//            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("오류발생");
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

//    @Test
//    public void getUser_테스트(){
//        SiteUser siteUser =this.userService.getUser("관리자");
//        this.orderService.create("30~50만원", "5명", "대충날짜",
//                "아니오", "부산", siteUser);
//    }

//    @Test
//    public void getOrder_테스트(){
//        SiteOrder order = orderService.getOrder(userService.getUser("관리자"));
//        System.out.println(order.getProd()+ "  " + order.getCash());
//
//        System.out.println(orderService.getOrder(userService.getUser("관리자")).getCash());
//    }

    @Test
    public void 크롤링테스트(){
        String url = "https://place-site.yanolja.com/places/23298";
        try {
            Document document = Jsoup.connect(url).get();
            String content = document.getElementsByClass("css-1muque2").text();
            System.out.println(content);

        }catch (Exception e){
            e.printStackTrace();
        }
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