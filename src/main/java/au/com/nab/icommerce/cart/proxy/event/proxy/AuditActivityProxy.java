package au.com.nab.icommerce.cart.proxy.event.proxy;


import au.com.nab.icommerce.cart.proxy.event.source.AuditActivitySource;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding({AuditActivitySource.class})
public class AuditActivityProxy {
}
