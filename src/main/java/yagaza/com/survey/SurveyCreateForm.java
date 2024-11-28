package yagaza.com.survey;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SurveyCreateForm {
    private String tourismType;

    private int tourismCount;

    private String restaurantType;

    private String hotelType;

    private boolean hotelChange;
}
