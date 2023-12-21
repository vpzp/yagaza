package yagaza.com.hotel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yagaza.com.DataNotFoundException;
import yagaza.com.user.SiteUser;

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

    public List<Hotel> getHotelList(){
        return hotelRepository.findAll();
    }
}
