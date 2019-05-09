package pluralsight.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomHealthCheck implements HealthIndicator {

    private int errorCode = 8; //set to not simulate down

    @Override
    public Health health() {
        log.info("Health check performed, error code is: {}", errorCode);

        // simulate app being down due to error code
        if (errorCode > 4 && errorCode < 8) {
            errorCode++;
            return Health.down().withDetail("Custom error code", errorCode).build();
        }

        errorCode++;

        return Health.up().build();
    }
}
