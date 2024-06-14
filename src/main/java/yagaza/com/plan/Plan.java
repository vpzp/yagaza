package yagaza.com.plan;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yagaza.com.order.SiteOrder;
import yagaza.com.survey.Survey;
import yagaza.com.user.SiteUser;

@Entity
@Getter
@Setter
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SiteUser siteUser;

    @OneToOne
    private Survey survey;

    @OneToOne
    private SiteOrder siteOrder;
}