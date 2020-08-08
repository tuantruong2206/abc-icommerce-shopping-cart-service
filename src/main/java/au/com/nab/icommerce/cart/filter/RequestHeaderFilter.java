package au.com.nab.icommerce.cart.filter;

import au.com.nab.icommerce.base.context.RequestHeaderHolderContext;
import au.com.nab.icommerce.base.filter.RequestFilter;
import org.springframework.stereotype.Component;

@Component
public class RequestHeaderFilter extends RequestFilter {

    private static final String BUSINESS_URL_PATTERN = "/cart/";

    public RequestHeaderFilter(RequestHeaderHolderContext requestHeaderHolderContext) {
        super(requestHeaderHolderContext);
    }

    @Override
    protected Boolean validateUrl(String s) {
        return s.contains(BUSINESS_URL_PATTERN);
    }
}
