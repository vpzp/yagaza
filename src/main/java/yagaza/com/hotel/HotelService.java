package yagaza.com.hotel;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yagaza.com.DataNotFoundException;
import yagaza.com.Geocoding;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.user.SiteUser;
import org.jsoup.nodes.Document;

import java.util.*;

@RequiredArgsConstructor
@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final Geocoding geocoding;

    public void createHotel(String title, String checkInTime, String img,String type,
                            int prod, Integer price, String region){
        Hotel hotel = new Hotel();
        hotel.setHotelName(title);
        hotel.setCheckInTime(checkInTime);
        hotel.setImg(img);
        hotel.setType(type);
        setHotelPrice(hotel, prod, price);
        hotel.setRegion(region);

        hotelRepository.save(hotel);
    }
    public Hotel createHotel(String title, String checkInTime, String img,String type,
                            String region){
        Hotel hotel = new Hotel();
        hotel.setHotelName(title);
        hotel.setCheckInTime(checkInTime);
        hotel.setImg(img);
        hotel.setType(type);
        hotel.setRegion(region);

        hotelRepository.save(hotel);

        return hotel;
    }

    public void setHotelPrice(Hotel hotel, Integer priceOnePerson, Integer priceTwoPerson,
                              Integer priceThreePerson, Integer priceFourPerson, Integer priceFivePerson){
        hotel.setPriceOnePerson(priceOnePerson);
        hotel.setPriceTwoPerson(priceTwoPerson);
        hotel.setPriceThreePerson(priceThreePerson);
        hotel.setPriceFourPerson(priceFourPerson);
        hotel.setPriceFivePerson(priceFivePerson);

        hotelRepository.save(hotel);
    }


    public void setHotelPrice(Hotel hotel, int prod, Integer price){
        switch (prod){
            case 1: hotel.setPriceOnePerson(price);
                break;
            case 2: hotel.setPriceTwoPerson(price);
                break;
            case 3: hotel.setPriceThreePerson(price);
                break;
            case 4: hotel.setPriceFourPerson(price);
                break;
            case 5: hotel.setPriceFivePerson(price);
                break;
        }

        hotelRepository.save(hotel);
    }

    public void deleteHotel(Hotel hotel){
        hotelRepository.delete(hotel);
    }

    public Hotel getHotel(long id) {
        Optional<Hotel> hotel = this.hotelRepository.findById(id);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new DataNotFoundException("hotel객체가 없습니다");
        }
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
    public void setMap(Hotel hotel) {
        Map<String, Double> map = geocoding.fecthMap(hotel.getHotelName());
        hotel.setMapX(map.get("x"));
        hotel.setMapY(map.get("y"));

        hotelRepository.save(hotel);
    }

    public void setAllMap(){
        List<Hotel> hotelList = getHotelList();
        for (Hotel hotel : hotelList) {
            setMap(hotel);
        }
    }

    public List<Hotel> getHotelType(String type){
        return hotelRepository.findAllByTypeContains(type);
    }

    public List<Hotel> getHotelList(){
        return hotelRepository.findAll();
    }

    public Page<Hotel> getPagingHotelList(int page, String keyword){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return hotelRepository.findAllByHotelNameContaining(keyword, pageable);
    }

    public Optional<Hotel> getHotelByHotelName(String hotelName, String region){
        return hotelRepository.findByHotelNameAndRegion(hotelName, region);
    }

}
