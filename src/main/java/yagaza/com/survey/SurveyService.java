package yagaza.com.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yagaza.com.DataNotFoundException;
import yagaza.com.Tourism.Tourism;
import yagaza.com.Tourism.TourismService;
import yagaza.com.hotel.Hotel;
import yagaza.com.hotel.HotelService;
import yagaza.com.order.SiteOrder;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.restaurant.RestaurantService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final HotelService hotelService;
    private final RestaurantService restaurantService;
    private final TourismService tourismService;

    public Survey create(String tourismType, int tourismCount, String restaurantType, List<String> openTime, String hotelType,
                       List<String> hotelKeyword, int hotelImportance, int restaurantImportance, boolean isHotelChange, SiteOrder siteOrder){
        Survey survey = new Survey();
        openTime.add("점심");
        openTime.add("저녁");
        survey.setTourismType(tourismType);
        survey.setTourismDayCount(tourismCount);
        survey.setRestaurantType(restaurantType);
        survey.setOpenTime(openTime);
        survey.setHotelType(hotelType);
        survey.setHotelKeyword(hotelKeyword);
        survey.setHotelImportance(hotelImportance);
        survey.setRestaurantImportance(restaurantImportance);
        survey.setHotelChange(isHotelChange);
        survey.setSiteOrder(siteOrder);

        this.surveyRepository.save(survey);

        return survey;
    }

    public int getRestaurantCash(int cash, Survey survey){
        int money = (int) (cash * 0.42);
        return money;
    }
    public int getHotelCash(int cash, Survey survey){
        int money = (int) (cash * 0.16);
        return money;
    }
    public List<Tourism> getTourism(Survey survey){
        List<Tourism> tourisms = new ArrayList<>();
        List<Tourism> tourismList = tourismService.getToursimList();
        List<Tourism> removed = new ArrayList<>();
        Collections.shuffle(tourismList);

        //tourismType이 포함된 관광지를 관광지 필요 숫자의 과반수 만큼 뽑음
        for (Tourism tourism : tourismList){
            if (tourism.getType().equals(survey.getTourismType()) &&
                    tourisms.size() < ((survey.getTourismDayCount() * (survey.getSiteOrder().getDate()) - 1) / 2 )){
                tourisms.add(tourism);
                removed.add(tourism);
            }
        }
        tourismList.removeAll(removed);
        //관광지 필요 숫자만큼 뽑음
        for (Tourism tourism : tourismList){
            if(tourisms.size() <(survey.getTourismDayCount() * (survey.getSiteOrder().getDate() - 1))){
                tourisms.add(tourism);
            }
        }
        Collections.shuffle(tourisms);

        return tourisms;
    }
    //List 2차원 배열 인덱스 0 : 점심 , 1 : 저녁 , 2 : 야식
    public List<Restaurant>[] getRestaurant(Survey survey){
        List<Restaurant>[] restaurantList = new ArrayList[3];
        int cash = getRestaurantCash(survey.getSiteOrder().getCash(), survey);
        List<Restaurant> list = restaurantService.getRestaurantList();
        int date = survey.getSiteOrder().getDate();
        //하루에 쓸수 있는 돈
        int oneDayCash = cash/ (date - 1) / survey.getSiteOrder().getProd();

        //금액에 맞는 식당 {점심, 저녁}리스트로 10개 반환
        restaurantList = getRestaurantTop15ByPrice(oneDayCash , false);

        //반환 된 15개의 데이터 순서 섞기
        List<Pair<Restaurant, Restaurant>> pairs = new ArrayList<>();
        for (int i = 0; i < restaurantList[0].size(); i++) {
            pairs.add(new Pair<>(restaurantList[0].get(i), restaurantList[1].get(i)));
        }
        Collections.shuffle(pairs);
        for (int i = 0; i < pairs.size(); i++) {
            restaurantList[0].set(i, pairs.get(i).getFirst());
            restaurantList[1].set(i, pairs.get(i).getSecond());
        }

        //날짜값만큼 리스트 남기기
        for (int j = 0 ; j < restaurantList.length ; j++){
            for (int i = restaurantList[j].size() - 1 ; i >= date - 1 ; i--){
                restaurantList[j].remove(i);
            }
        }

        return restaurantList;
    }
    public List<Hotel> getHotel(Survey survey){
        List<Hotel> hotelList = new ArrayList<>();
        int cash = getHotelCash(survey.getSiteOrder().getCash(), survey);
        hotelList = hotelService.getHotelTypeAndKeyword(survey.getHotelType(), survey.getHotelKeyword());
        cash = cash / (survey.getSiteOrder().getDate() - 1);

        //cash == null인 호텔 제거
        hotelList.removeIf(hotel -> hotelService.getHotelPrice(survey.getSiteOrder().getProd(), hotel) == null);

        //호텔을 10개만 남김
        hotelList = getHotelTop10ByPrice(hotelList, cash, survey.getSiteOrder().getProd());

        //10개의 호텔중에서 date에따라 선택
        hotelList =  getHotelByDate(hotelList, survey.getSiteOrder().getDate(), survey.isHotelChange());

        return hotelList;
    }
//    cash에 가장 금액이 가까운 레스토랑 10개 list<Restaurant>[]로 리턴
    public List<Restaurant>[] getRestaurantTop15ByPrice(int oneDayCash, boolean isMidnight){
        List<Restaurant>[] restaurants = new ArrayList[3];
        for(int i = 0 ; i < restaurants.length ; i++){
            restaurants[i] = new ArrayList<>();
        }
        List<Restaurant> launchRestaurantList = restaurantService.getRestaurantListByOpenTime("점심");
        List<Restaurant> dinnerRestaurantList = restaurantService.getRestaurantListByOpenTime("저녁");

        for(int j = 0 ; j < 15 ; j ++){
            int launchIndex = 0;
            int dinnerIndex = 0;
            int min = Integer.MAX_VALUE;

            for (int i = 0 ; i < launchRestaurantList.size() ; i++){
                if (launchRestaurantList.get(i).getPrice()[0] == 0){
                    continue;
                }
                for (int k = 0 ; k < dinnerRestaurantList.size() ; k++){
                    if (dinnerRestaurantList.get(k).getPrice()[1] == 0){
                        continue;
                    }
                    if (isMidnight){

                    }else {
                        //abs는 cash - (점심 + 저녁 값) 또는 cash - (점심 + 저녁 + 야식)가격
                        int abs = Math.abs(oneDayCash - (launchRestaurantList.get(i).getPrice()[0] + dinnerRestaurantList.get(k).getPrice()[1]));
                        if (abs < min){
                            min = abs;
                            launchIndex = i;
                            dinnerIndex = k;
                        }
                    }
                }
            }

            restaurants[0].add(launchRestaurantList.get(launchIndex));
            restaurants[1].add(dinnerRestaurantList.get(dinnerIndex));

            launchRestaurantList.remove(launchIndex);
            if(launchRestaurantList.contains(dinnerRestaurantList.get(dinnerIndex))){
                launchRestaurantList.remove(dinnerRestaurantList.get(dinnerIndex));
            }

            dinnerRestaurantList.remove(dinnerIndex);
            dinnerRestaurantList.remove(launchRestaurantList.get(launchIndex));
        }
        return restaurants;
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

    public Survey getById(Long id){
        Optional<Survey> survey = this.surveyRepository.findById(id);
        if (survey.isPresent()){
            return survey.get();
        }else {
            throw new DataNotFoundException("survey값이 존재하지 않습니다.");
        }
    }

    static class Pair<T, U> {
        private T first;
        private U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }
    }

}
