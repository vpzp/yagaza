package yagaza.com.crawling;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
    String url = "https://www.yanolja.com/search/%EB%B6%80%EC%82%B0?keyword=%EB%B6%80%EC%82%B0";
    Connection conn = Jsoup.connect(url);

    //TODO 크롤링 하기 야놀자에 "부산"검색했을때 나오는 호텔들의 "places/1018909" 뒷번호를 따서 데이터베이스에 저장하기저장하고
    // - 그걸토대로 places/번호 에 들어가서 객실정보, 리뷰, 가격, 객실의 빈방 유무 따와서 데이터베이스에 저장하기.
        public void hotelCrawling(){
        try{
            Document document = conn.get();
            Elements hotelTitleElements = document.select("div.PlaceListTitle_container__qe7XH > strong.PlaceListTitle_text__2511B");

            System.out.println("사이즈는 " + hotelTitleElements.size());
            for(int i = 0 ; i < hotelTitleElements.size() ; i++){
                String title = hotelTitleElements.get(i).text();
                System.out.println("출력은 : "+ title);
            }
            System.out.println("출력");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Crawling crawling = new Crawling();
        crawling.hotelCrawling();
    }
}
