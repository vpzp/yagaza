package yagaza.com.order;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderCreateForm {
    //TODO 예외값 처리 해줘야함
    @NotNull()
    @Min(value = 10000)
    @Max(value = 10000000)
    @Digits(integer = 7, fraction = 0, message = "옳바른 값을 입력해주세요")
    int cash;

    int prod;

    int date;


    String travel;

}
