package pluralsight.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(Sink.class)
@SpringBootApplication
public class PluralsightStreamTollintakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PluralsightStreamTollintakeApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void log(String msg) {
        log.info(msg);
    }
}
