package yagaza.com.admin.requestHotel;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestHotelForm {
    @Size(min = 2, max = 10)
    @NotEmpty(message = "호텔이름을 입력해주세요.")
    private String hotelName;

    @NotEmpty(message = "지역을 입력해주세요.")
    private String region;

    @Digits(integer = 8, fraction = 0, message = "숫자만 입력 가능합니다.")
    private Integer priceOnePerson = 0;

    @Digits(integer = 8, fraction = 0, message = "숫자만 입력 가능합니다.")
    private Integer priceTwoPerson = 0;

    @Digits(integer = 8, fraction = 0, message = "숫자만 입력 가능합니다.")
    private Integer priceThreePerson= 0;

    @Digits(integer = 8, fraction = 0, message = "숫자만 입력 가능합니다.")
    private Integer priceFourPerson = 0;

    @Digits(integer = 8, fraction = 0, message = "숫자만 입력 가능합니다.")
    private Integer priceFivePerson = 0;

    @Pattern(regexp = "^https?://.*\\.(jpg|jpeg|png|gif)$", message = "유효한 이미지 URL을 입력하세요. (예: http://example.com/image.jpg)")
    @NotEmpty(message = "이미지값을 입력해주세요.")
    private String img;

    @NotEmpty(message = "타입을 입력해주세요.")
    private String type;

    @NotEmpty(message = "체크인 시간을 입력해주세요.")
    private String checkInTime = "";
}
