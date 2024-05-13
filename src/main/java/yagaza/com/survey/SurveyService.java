package yagaza.com.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yagaza.com.hotel.Hotel;
import yagaza.com.hotel.HotelService;
import yagaza.com.order.SiteOrder;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final HotelService hotelService;

    public void create(String restaurantType, List<String> openTime, String hotelType, List<String> hotelKeyword,
                       int hotelImportance, int restaurantImportance, boolean isHotelChange, SiteOrder siteOrder){
        Survey survey = new Survey();
        survey.setRestaurantType(restaurantType);
        survey.setOpenTime(openTime);
        survey.setHotelType(hotelType);
        survey.setHotelKeyword(hotelKeyword);
        survey.setHotelImportance(hotelImportance);
        survey.setRestaurantImportance(restaurantImportance);
        survey.setHotelChange(isHotelChange);
        survey.setSiteOrder(siteOrder);

        this.surveyRepository.save(survey);

    }

    public int getRestaurantCash(int cash, Survey survey){
        int money = (int) (cash * 0.42);
        return money;
    }
    public int getHotelCash(int cash, Survey survey){
        int money = (int) (cash * 0.16);
        return money;
    }
    public List<Hotel> getHotel(Survey survey){
        List<Hotel> hotelList = new ArrayList<>();
        int cash = getHotelCash(survey.getSiteOrder().getCash(), survey);
        hotelList = hotelService.getHotelTypeAndKeyword(survey.getHotelType(), survey.getHotelKeyword());
        cash = cash / (Integer.parseInt(survey.getSiteOrder().getDate()) - 1);

        //cash == null인 호텔 제거
        hotelList.removeIf(hotel -> hotelService.getHotelPrice(survey.getSiteOrder().getProd(), hotel) == null);

        //호텔을 10개만 남김
        hotelList = getHotelTop10ByPrice(hotelList, cash, survey.getSiteOrder().getProd());

        //10개의 호텔중에서 date에따라 선택
        hotelList =  getHotelByDate(hotelList, Integer.parseInt(survey.getSiteOrder().getDate()), survey.isHotelChange());

        return hotelList;
    }

    //cash에 가장 금액이 가까운 호텔 10개 list<hotel>로 리턴
    public List<Hotel> getHotelTop10ByPrice(List<Hotel> hotelList, int cash, int prod){
        List<Hotel> hotels = new ArrayList<>();
        for(int j = 0 ; j < 10 ; j ++){
            int index = 0;
            int min = Integer.MAX_VALUE;

            for (int i = 0 ; i < hotelList.size() ; i++){
                int abs = Math.abs(cash - hotelService.getHotelPrice(prod, hotelList.get(i)));
                if (abs < min){
                    min = abs;
                    index = i;
                }
            }

            hotels.add(hotelList.get(index));
            hotelList.remove(index);
        }
        return hotels;
    }
    public static int abs(int number) {
        return (number < 0 ) ? -number : number;
    }

    //date크기 만큼 hotelList리턴
    public List<Hotel> getHotelByDate(List<Hotel> hotelList, int date, boolean isHotelChange){
        List<Hotel> list = new ArrayList<>();
        Collections.shuffle(hotelList);

        for (int i = 0 ; i < date - 1 ; i++){
            if(isHotelChange){
                list.add(hotelList.get(i));
            }else {
                list.add(hotelList.get(0));
            }
        }
        return list;
    }
}
