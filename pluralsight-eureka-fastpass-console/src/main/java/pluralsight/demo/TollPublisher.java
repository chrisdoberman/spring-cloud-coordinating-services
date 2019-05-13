package pluralsight.demo;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Random;

// @EnableBinding(Source.class)
@EnableBinding(TollSource.class)
public class TollPublisher {

    private Random random = new Random();

    // commenting out to use custom use TollSource
    // defaults to send this message 1/second
    /*@InboundChannelAdapter(channel = Source.OUTPUT)
    public String sendTollCharge() {
        return "{station:\"20\", customerid: \"100\", timestamp: \"2019-05-11-12T03:15:00\"}";
    }*/

    //  send this message every 2 seconds
    @Bean
    // commenting out to use the rest controller to publish messages
    //@InboundChannelAdapter(channel = "fastpassTollChannel", poller = @Poller(fixedDelay = "2000"))
    public MessageSource<Toll> sendTollCharge() {
        //return "{station:\"20\", customerid: \"100\", timestamp: \"2019-05-11-12T03:15:00\"}";
        return () -> MessageBuilder.withPayload(new Toll("20", "100", "2019-05-11-12T03:15:00"))
                .setHeader("speed", random.nextInt(8) * 10).build();
    }

}
