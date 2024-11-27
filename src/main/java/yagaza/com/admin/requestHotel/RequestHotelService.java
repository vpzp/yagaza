package yagaza.com.admin.requestHotel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yagaza.com.DataNotFoundException;
import yagaza.com.hotel.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestHotelService {
    private final RequestHotelRepository requestHotelRepository;

    public void create(String hotelName, String region, Integer priceOnePerson, Integer priceTwoPerson,
                       Integer priceThreePerson, Integer priceFourPerson, Integer priceFivePerson,
                       String img, String type, String checkInTime, String userId){
        RequestHotel requestHotel = new RequestHotel();
        requestHotel.setHotelName(hotelName)
                .setRegion(region)
                .setPriceOnePerson(priceOnePerson)
                .setPriceTwoPerson(priceTwoPerson)
                .setPriceThreePerson(priceThreePerson)
                .setPriceFourPerson(priceFourPerson)
                .setPriceFivePerson(priceFivePerson)
                .setImg(img)
                .setType(type)
                .setCheckInTime(checkInTime)
                .setUserId(userId)
                .setStatus("대기중");

        requestHotelRepository.save(requestHotel);
    }

    public Page<RequestHotel> getPagingHotelList(int page, String keyword){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return requestHotelRepository.findAllByHotelNameContaining(keyword, pageable);
    }

    public RequestHotel getHotel(Long id) {
        Optional<RequestHotel> hotel = requestHotelRepository.findById(id);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new DataNotFoundException("hotel객체가 없습니다");
        }
    }

    public RequestHotel updateStatus(Long id, String status) {
        RequestHotel hotel = getHotel(id);
        hotel.setStatus(status);

        requestHotelRepository.save(hotel);

        return hotel;
    }

    public Page<RequestHotel> getPagingMyHotelList(int page, String userId  ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return requestHotelRepository.findAllByUserId(userId, pageable);
    }
}
