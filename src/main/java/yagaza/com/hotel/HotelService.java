package yagaza.com.hotel;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import yagaza.com.DataNotFoundException;
import yagaza.com.user.SiteUser;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public Hotel createHrefName(Long id, String href, String name){
        Hotel hotel = new Hotel();
        hotel.setId(id);
        hotel.setHotelHref(href);
        hotel.setHotelName(name);

        this.hotelRepository.save(hotel);

        return hotel;
    }

    public void createRegionImgContent(long id, String region, String img, String content){
        Hotel hotel = getHotel(id);
        hotel.setRegion(region);
        hotel.setImg(img);
        hotel.setContent(content);

        this.hotelRepository.save(hotel);
    }
    public Hotel getHotel(long id) {
        Optional<Hotel> hotel = this.hotelRepository.findById(id);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new DataNotFoundException("hotel객체가 없습니다");
        }
    }

    public List<String> getHotelReview(long id){
        Optional<Hotel> hotel = this.hotelRepository.findById(id);
        String url = hotel.get().getHotelHref();
        String review = "";
        try {
            Document document = Jsoup.connect(url).get();
            review = document.getElementsByClass("css-4a3h28").text();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("setHotelKeyword 오류 발생");
        }
        System.out.println(review);
        System.out.println(getHotelKeyword(review).toString());

        return getHotelKeyword(review);
    }
    public List<String> getHotelKeyword(String review){
        List<String> keyword = new ArrayList<>();

        //편리한 위치, 편의시설, 깔끔한, 친절한, 경치, 가성비, 조용한
        String[][] word = {{"편리한 위치", "위치", "거리", "가까", "인프라", "접근"}
                , {"편의 시설", "편의점", "주차", "스낵바"}
                , {"깔끔한", "깨끗", "깔끔", "청결"}
                , {"친절한", "친절", "편안", "편히", "아늑"}
                , {"경치", "뷰", "바닷가", "바다"}
                , {"가성비", "가격", "저렴"}
                , {"조용한", "조용", "방음"}};
        for (int i = 0 ; i < word.length ; i++){
            for(int j = 0 ; j < word[i].length ; j++){
                if (review.contains(word[i][j])){
                    keyword.add(word[i][0]);
                    break;
                }
            }
        }

        return keyword;
    }

    public List<Hotel> getHotelTypeAndKeyword(String type, List<String> keyword){
        List<Hotel> hotelList = getHotelList();
        List<Hotel> hotels = new ArrayList<>();
        for (int i = 0 ; i < hotelList.size() ; i++){
            switch (keyword.size()){
                case 1: if(hotelList.get(i).getType().equals(type) && hotelList.get(i).getKeyword().contains(keyword.get(0))){
                    hotels.add(hotelList.get(i));
                }
                break;
                case 2: if(hotelList.get(i).getType().equals(type) && hotelList.get(i).getKeyword().contains(keyword.get(0))
                    && hotelList.get(i).getKeyword().contains(keyword.get(1))){
                    hotels.add(hotelList.get(i));
                }
                break;
            }
        }
        return hotels;
    }

    public Integer getHotelPrice(int prod, Hotel hotel){
        switch (prod){
            case 1: return hotel.getPriceOnePerson();
            case 2: return hotel.getPriceTwoPerson();
            case 3: return hotel.getPriceThreePerson();
            case 4: return hotel.getPriceFourPerson();
            case 5: return hotel.getPriceFivePerson();
        }
        return hotel.getPriceOnePerson();
    }

    public List<Hotel> getHotelList(){
        return hotelRepository.findAll();
    }
}
