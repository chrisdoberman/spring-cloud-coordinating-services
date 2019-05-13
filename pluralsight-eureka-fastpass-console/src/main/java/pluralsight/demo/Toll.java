package pluralsight.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Toll {

    private String stationId;
    private String customerId;
    private String timestamp;
}
