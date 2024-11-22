package yagaza.com.admin.requestHotel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestHotelService {
    private final RequestHotelRepository requestHotelRepository;

    public void create(String hotelName, String region, Integer priceOnePerson, Integer priceTwoPerson,
                       Integer priceThreePerson, Integer priceFourPerson, Integer priceFivePerson,
                       String img, String type, String checkInTime){
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
                .setStatus("대기중");

        requestHotelRepository.save(requestHotel);
    }

}
