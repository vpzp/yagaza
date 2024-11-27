package yagaza.com.survey;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.order.SiteOrder;

import java.util.List;

@Entity
@Getter
@Setter
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //질문 1: 어떤 종류의 관광지를 선호하시나요?
    private String tourismType;

    //질문 2: 여행 갔을때 내 모습은?
    private int tourismDayCount;

    //질문 4: 어떤 종류의 음식을 선호하시나요?
    private String restaurantType;

    //질문 3: 하루에 식사를 얼마나 하실 계획이신가요?
    private List<String> openTime;

    //질문 5: 어떤 종류의 숙소를 선호하시나요?
    private String hotelType;

    //질문 8: 내가 생각했을 때 여행에서의 숙소 중요도
    private int hotelImportance;

    //질문 9: 내가 생각했을 때 여행에서의 식사 중요도
    private int restaurantImportance;

    //질문 11: 여행하는 동안 숙소는 어떻게 할까요?
    private boolean isHotelChange;

    @OneToOne(cascade = CascadeType.REMOVE)
    private SiteOrder siteOrder;
}
