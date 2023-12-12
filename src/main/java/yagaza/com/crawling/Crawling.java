package yagaza.com.crawling;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {

    //TODO 크롤링 하기 야놀자에 "부산"검색했을때 나오는 호텔들의 "places/1018909" 뒷번호를 따서 데이터베이스에 저장하기저장하고
    // - 그걸토대로 places/번호 에 들어가서 객실정보, 리뷰, 가격, 객실의 빈방 유무 따와서 데이터베이스에 저장하기.
    // - 리뷰 따와야함, hotel에 저장하기
    // - 호텔 예약 날짜, 인원수 입력받고 가변변수(가격, 예약 가능 유무)들 갱신하기
    public void hotelNumberCrawling(){
        String url = "https://www.yanolja.com/search/%EB%B6%80%EC%82%B0?keyword=%EB%B6%80%EC%82%B0";
        Connection conn = Jsoup.connect(url);
        try{
            Document document = conn.get();
            Elements hotelTitleElements = document.getElementsByClass("common_clearfix__M6urU");
            System.out.println("hotelTitleElements = " + hotelTitleElements.text());

//            System.out.println("사이즈는 " + hotelTitleElements.size());
//            for(int i = 0 ; i < hotelTitleElements.size() ; i++){
//                String title = hotelTitleElements.get(i).text();
//                System.out.println("출력은 : "+ title);
//            }
//            System.out.println("출력");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void hotelCrawling(String placeId){
//        placeId = "1000109332";
        String url = "https://place-site.yanolja.com/places/" + placeId;
        System.out.println("호텔Id는 : " + placeId);
        try{
            Document document = Jsoup.connect(url).get();
            String hotelName = document.getElementsByClass("css-1g3ik0v").text();
            System.out.println("호텔 이름은 : " + hotelName);
            String adress = document.getElementsByClass("address css-18ufud2").text();
            System.out.println("주소는 : " + adress);
            Elements imgElement = document.getElementsByClass("css-sr2c7j");
            System.out.println("이미지주소는 : " + imgElement.attr("src"));
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
    public static void main(String[] args){
        Crawling crawling = new Crawling();
        crawling.hotelNumberCrawling();
//        crawling.hotelCrawling("1000109332");
    }
}
