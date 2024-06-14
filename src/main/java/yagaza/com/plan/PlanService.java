package yagaza.com.plan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yagaza.com.order.SiteOrder;
import yagaza.com.survey.Survey;
import yagaza.com.user.SiteUser;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;

    public void save(SiteUser siteUser, Survey survey){
        Plan plan = new Plan();
        plan.setSiteUser(siteUser);
        plan.setSurvey(survey);
        plan.setSiteOrder(survey.getSiteOrder());

        this.planRepository.save(plan);
    }

}
