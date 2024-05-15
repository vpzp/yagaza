package yagaza.com.Tourism;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yagaza.com.survey.SurveyService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TourismService {
    @Autowired
    private final TourismRepository tourismRepository;

    public void create(String name, String imgUrl, String content, String region, String address, int price, String type){
        Tourism tourism = new Tourism();
        tourism.setName(name);
        tourism.setImgUrl(imgUrl);
        tourism.setContent(content);
        tourism.setRegion(region);
        tourism.setAddress(address);
        tourism.setPrice(price);
        tourism.setType(type);

        this.tourismRepository.save(tourism);
    }

    public List<Tourism> getToursimList(){
        return this.tourismRepository.findAll();
    }

}
