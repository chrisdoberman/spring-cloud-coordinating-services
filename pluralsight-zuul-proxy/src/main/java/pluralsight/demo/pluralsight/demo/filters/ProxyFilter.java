package pluralsight.demo.pluralsight.demo.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProxyFilter extends ZuulFilter {

    @Override
    public String filterType() {

        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {

        boolean isMobile = false;
        RequestContext ctx = RequestContext.getCurrentContext();
        String param = ctx.getRequest().getParameter("source");

        if (param != null && param.equals("mobile")) {
            isMobile = true;
        }
        return isMobile;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("Calling filter!");
        return null;
    }
}
