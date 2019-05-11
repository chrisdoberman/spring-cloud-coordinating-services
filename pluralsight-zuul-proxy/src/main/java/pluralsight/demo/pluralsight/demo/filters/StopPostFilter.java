package pluralsight.demo.pluralsight.demo.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public class StopPostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        Instant stop = Instant.now();

        // get start value
        RequestContext ctx = RequestContext.getCurrentContext();
        Instant start = (Instant) ctx.get("starttime");

        long secondsDifference = ChronoUnit.MILLIS.between(start, stop);

        log.info("Call took {} milliseconds", secondsDifference);
        return null;
    }
}
