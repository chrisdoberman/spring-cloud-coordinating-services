package pluralsight.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class PluralsightZuulProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PluralsightZuulProxyApplication.class, args);
    }

}
