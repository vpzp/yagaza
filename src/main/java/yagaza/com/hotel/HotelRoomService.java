package yagaza.com.hotel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yagaza.com.DataNotFoundException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HotelRoomService {
    private final HotelRepository hotelRepository;
    private final HotelRoomRepository hotelRoomRepository;

    public HotelRoom create(String id, String name, String href, String checkInTime, String price, String content, long placeId){
        HotelRoom hotelRoom = new HotelRoom();
        hotelRoom.setId(id);
        hotelRoom.setName(name);
        hotelRoom.setHref(href);
        hotelRoom.setCheckInTime(checkInTime);
        hotelRoom.setPrice(price);
        hotelRoom.setContent(content);
        hotelRoom.setHotel(hotelRepository.findById(placeId).get());

        this.hotelRoomRepository.save(hotelRoom);
        return hotelRoom;
    }

    public HotelRoom getHotelRoom(String id) {
        Optional<HotelRoom> hotelRoom = this.hotelRoomRepository.findById(id);
        if (hotelRoom.isPresent()) {
            return hotelRoom.get();
        } else {
            throw new DataNotFoundException("hotelRoom객체가 없습니다");
        }
    }
}
