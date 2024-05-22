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

    private List<String> openTime;

    private String hotelType;

    private List<String> hotelKeyword;

    private int hotelImportance;

    private int restaurantImportance;

    private boolean hotelChange;
}
