package pluralsight.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Slf4j
// when using eureka you don't have to specify the name field here, just being explicit
@RibbonClient(name = "pluralsight-fastpass-service", configuration = TollClientRoutingConfig.class)
@Controller
public class FastPassController {

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Autowired
	private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFastPassCustomerDetailsBackup")
	@RequestMapping(path="/customerdetails", params={"fastpassid"})
	public String getFastPassCustomerDetails(@RequestParam String fastpassid, Model m) {
		
//		RestTemplate rest = new RestTemplate();
//		FastPassCustomer c = rest.getForObject("http://localhost:8086/fastpass?fastpassid=" + fastpassid, FastPassCustomer.class);
		FastPassCustomer c =
                restTemplate.getForObject("http://pluralsight-fastpass-service/fastpass?fastpassid=" + fastpassid,
                        FastPassCustomer.class);
		log.info("retrieved customer details");
		m.addAttribute("customer", c);
		return "console";
	}

	public String getFastPassCustomerDetailsBackup(@RequestParam String fastpassid, Model m) {

		log.info("Fallback operation called");
		FastPassCustomer c = new FastPassCustomer();
		c.setFastPassId(fastpassid);
		m.addAttribute("customer", c);
		return "console";
	}

}
