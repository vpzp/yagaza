package yagaza.com.admin.requestRestaurant;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestRestaurantForm {
    @Size(min = 2, max = 10)
    @NotEmpty(message = "음식점 이름을 입력해주세요.")
    private String name;

    @NotEmpty(message = "지역을 입력해주세요.")
    private String region;

    @NotEmpty(message = "음식점 설명을 입력해주세요.")
    private String content;

    private int[] price;

    private List<String> openTime;

    @NotEmpty(message = "음식점 종류를 선택해주세요.")
    private String type;

    private List<String> img;

}
