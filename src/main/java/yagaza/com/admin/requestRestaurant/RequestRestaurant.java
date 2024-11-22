package yagaza.com.admin.requestRestaurant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Entity
@Accessors(chain=true)
public class RequestRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String region;

    private String content;

    //인덱스 0은 점심 가격 1은 저녁 가격
    private int[] price;

    private List<String> openTime;

    private String type;

    private List<String> img;

    private String status;
}
