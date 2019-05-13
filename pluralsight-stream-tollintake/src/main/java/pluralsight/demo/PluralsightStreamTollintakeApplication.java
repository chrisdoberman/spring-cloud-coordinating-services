package pluralsight.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(Sink.class)
//@EnableBinding(Processor.class)
@SpringBootApplication
public class PluralsightStreamTollintakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PluralsightStreamTollintakeApplication.class, args);
    }

    //demo conditional logic in streamlistener
    @StreamListener(target = Sink.INPUT, condition = "headers['speed'] > 40")
    public void logFast(String msg) {
        log.info("fast - {}", msg);
    }

    //demo conditional logic in streamlistener
    @StreamListener(target = Sink.INPUT, condition = "headers['speed'] <= 40")
    public void logSlow(String msg) {
        log.info("slow - {}", msg);
    }

    // demo a processor
    //@StreamListener(Sink.INPUT)
    /*@StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public String log(String msg) {
        log.info(msg);
        return msg;
    }*/
}
