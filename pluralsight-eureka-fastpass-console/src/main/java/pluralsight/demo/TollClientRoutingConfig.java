package pluralsight.demo;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.context.annotation.Bean;

// don't mark this as Configuration otherwise it will apply to every ribbon client in the project
// either put it in a sep package or explicit configure it in the @RibbonClient on the controller
// which is what we're doing here.
public class TollClientRoutingConfig {

    //@Autowired
    private IClientConfig ribbonClientConfig;

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new WeightedResponseTimeRule();
    }
}
