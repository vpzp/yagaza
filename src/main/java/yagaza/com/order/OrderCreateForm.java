package yagaza.com.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderCreateForm {
    //TODO 예외값 처리 해줘야함
    int cash;

    int prod;

    int date;


    String travel;

}
