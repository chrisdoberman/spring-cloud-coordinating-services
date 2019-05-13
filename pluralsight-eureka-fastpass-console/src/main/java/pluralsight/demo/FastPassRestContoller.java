package pluralsight.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
public class FastPassRestContoller {

    private TollSource tollSource;

    public FastPassRestContoller(TollSource tollSource) {
        this.tollSource = tollSource;
    }

    @PostMapping(path = "toll")
    public String publishMessage(@RequestBody String payload) {
        log.info(payload);
        Random r = new Random();
        tollSource.fastpassToll().send(MessageBuilder.withPayload(payload)
                .setHeader("speed", r.nextInt(8) * 10).build());
        return "success";
    }
}
