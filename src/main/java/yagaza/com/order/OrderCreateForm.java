package yagaza.com.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateForm {
    String cash;

    String prod;

    String date;

    String car;

    String travel;

}
