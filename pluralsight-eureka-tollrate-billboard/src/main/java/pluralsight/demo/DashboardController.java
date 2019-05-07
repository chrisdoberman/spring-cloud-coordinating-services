package pluralsight.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
public class DashboardController {

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getTollRateBackup")
	@RequestMapping("/dashboard")
	public String getTollRate(@RequestParam int stationId, Model m) {

		// use the injected resttemplate that uses a load balanced client that is set at run time
		// and use the registry name instead of hardcoded url.
		//RestTemplate rest = new RestTemplate();
		//TollRate tr = rest.getForObject("http://localhost:8085/tollrate/" + stationId, TollRate.class);
		TollRate tr = restTemplate.getForObject("http://pluralsight-toll-service/tollrate/" + stationId,
				TollRate.class);
		log.info("stationId: {}", stationId);
		m.addAttribute("rate", tr.getCurrentRate());
		return "dashboard";
	}

	public String getTollRateBackup(@RequestParam int stationId, Model m) {
		log.info("Fallback operation called!");
		m.addAttribute("rate", "1.00");
		return "dashboard";
	}
}
