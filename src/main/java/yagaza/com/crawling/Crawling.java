package yagaza.com.crawling;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yagaza.com.hotel.HotelService;

import java.util.Base64;
import java.util.Date;
import java.util.List;

public class Crawling {

    //TODO 크롤링 하기 야놀자에 "부산"검색했을때 나오는 호텔들의 "places/1018909" 뒷번호를 따서 데이터베이스에 저장하기저장하고
    // - 그걸토대로 places/번호 에 들어가서 객실정보, 리뷰, 가격, 객실의 빈방 유무 따와서 데이터베이스에 저장하기.
    // - 리뷰 따와야함, hotel에 저장하기
    // - 호텔 예약 날짜, 인원수 입력받고 가변변수(가격, 예약 가능 유무)들 갱신하기
    public void hotelNumberCrawling(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://www.yanolja.com/search/%EB%B6%80%EC%82%B0?keyword=%EB%B6%80%EC%82%B0";
        driver.get(url);
        System.out.println("성공");

        try {
            WebElement hotels = driver.findElement(By.className("common_clearfix__M6urU"));
            var stTime = new Date().getTime();
            while (new Date().getTime() < stTime + 60000) { //30초 동안 무한스크롤 지속
                Thread.sleep(500); //리소스 초과 방지
                ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)", hotels);
            }
            System.out.println(hotels.getAttribute("href"));
            List<WebElement> hotelElements = driver.findElements(By.className("common_clearfix__M6urU"));
            System.out.println(hotelElements.size());
            for (WebElement hotelElement : hotelElements){
                System.out.println(hotelElement.getAttribute("href"));
                System.out.println(hotelElement.getAttribute("title"));
            }
        }catch (Exception e){
            System.out.println("오류발생");
            e.printStackTrace();
        }
    }
    public void hotelCrawling(long placeId){
//        placeId = "23298";
        String url = "https://place-site.yanolja.com/places/" + placeId;
        Base64.Decoder decode = Base64.getDecoder();
        try{
            Document document = Jsoup.connect(url).get();
            String hotelName = document.getElementsByClass("css-1g3ik0v").text();
            System.out.println("호텔 이름은 : " + hotelName);
            String adress = document.getElementsByClass("address css-18ufud2").text();
            System.out.println("주소는 : " + adress);
            Elements imgElement = document.getElementsByClass("css-sr2c7j");
            System.out.println("이미지 주소는 : " + imgElement.attr("src"));
            System.out.println();

            Elements hotelRoomElements = document.getElementsByClass("css-1nnj57j");
            for(Element element : hotelRoomElements){
                //숙박가능한 객실만 체크하는 조건문
                //TODO 호텔룸의 이미지 주소값 받아오기, 호텔룸 테이블 생성하는 코드 작성
                if(isLodgment(element)){
                    System.out.println("객실 이름은 : "+ element.getElementsByClass("css-1rr4h0w").text());
                    System.out.println("href 주소는 : " + element.getElementsByClass("css-1w7jlh2").attr("href"));
                    System.out.println("체크인 시간은 : " + element.getElementsByClass("css-1qxtkjb").text());
                    System.out.println("가격은 : "+ element.getElementsByClass("price").text());
                    System.out.println("content는 : "+ element.getElementsByClass("css-1w8cj78").text());
                    System.out.println();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isLodgment(Element element){
        if(element.getElementsByClass("css-dh6x9t").text().contains("숙박")){
            return true;
        }
        return false;
    }
    public long hrefToId(String href){
        long id = Long.parseLong(href.replaceAll("[^0-9]", ""));
        return id;
    }
    public static void main(String[] args){
        Crawling crawling = new Crawling();
        crawling.hotelNumberCrawling();
       // crawling.hotelCrawling(23298);
    }
}
