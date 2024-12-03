package yagaza.com.order;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderCreateForm {
    //TODO 예외값 처리 해줘야함
//    @Digits("숫자를 입력해주세요", )
    int cash;

    int prod;

    int date;


    String travel;

}
