package yagaza.com.admin.requestRestaurant;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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

    private List<Integer> price;

    @NotEmpty(message = "운영 시간을 최소 하나 이상 선택해주세요.")
    private List<String> openTime;

    @NotEmpty(message = "음식점 종류를 선택해주세요.")
    private String type;

    private List<String> img;

}
