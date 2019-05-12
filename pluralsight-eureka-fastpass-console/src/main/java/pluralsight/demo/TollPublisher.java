package pluralsight.demo;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;

@EnableBinding(Source.class)
public class TollPublisher {

    // defaults to send this message 1/second
    @InboundChannelAdapter(channel = Source.OUTPUT)
    public String sendTollCharge() {
        return "{station:\"20\", customerid: \"100\", timestamp: \"2019-05-11-12T03:15:00\"}";
    }

}
