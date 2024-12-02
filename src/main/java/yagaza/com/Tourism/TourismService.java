package yagaza.com.Tourism;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yagaza.com.Geocoding;
import yagaza.com.restaurant.Restaurant;
import yagaza.com.survey.SurveyService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TourismService {
    private final TourismRepository tourismRepository;
    private final Geocoding geocoding;

    public void create(String name, String imgUrl, String region, String address, int price, String type){
        Tourism tourism = new Tourism();
        tourism.setName(name);
        tourism.setImgUrl(imgUrl);
        tourism.setRegion(region);
        tourism.setAddress(address);
        tourism.setPrice(price);
        tourism.setType(type);

        this.tourismRepository.save(tourism);
    }

    public List<Tourism> getToursimList(){
        return this.tourismRepository.findAll();
    }

    public List<Tourism> getToursimList(String region){
        return this.tourismRepository.findAllByRegion(region);
    }



    public void setAllMap(){
        List<Tourism> tourismList = getToursimList();
        for (Tourism tourism : tourismList) {
            setMap(tourism);
        }
    }

    public void setMap(Tourism tourism) {

        Map<String, Double> map = geocoding.fecthMap(tourism.getName());
        tourism.setMapX(map.get("x"));
        tourism.setMapY(map.get("y"));

        tourismRepository.save(tourism);
    }
}
