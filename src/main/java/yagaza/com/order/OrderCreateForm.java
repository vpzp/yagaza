package yagaza.com.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateForm {
    //TODO 예외값 처리 해줘야함
    int cash;

    int prod;

    int date;

    String car;

    String travel;

}
