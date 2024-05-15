package yagaza.com.hotel;

import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class HotelKeywordText {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    HotelService hotelService;
    @Test
    public void HotelKeywordText(){
        List<Hotel> hotelList = hotelRepository.findAll();
        for (Hotel hotel : hotelList){
            hotel.setKeyword(hotelService.getHotelReview(hotel.getId()));
            if (hotelService.getHotelReview(hotel.getId()) == null){
                continue;
            }
            this.hotelRepository.save(hotel);
        }
    }
}
