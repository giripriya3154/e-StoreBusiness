package cs.miu.edu.gateway_service;

//import cs.miu.edu.gateway_service.config.security.JwtUserDetailsService;
import cs.miu.edu.gateway_service.filter.AuthenticationFilter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GatewayServiceApplication {

	@Autowired
	private AuthenticationFilter filter;


	@Value("${shipping.service.key}")
	private String shippingServiceKey;

	@Value("${order.service.key}")
	private String orderServiceKey;

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
//	@Bean
//	@LoadBalanced
//	public WebClient.Builder loadBalancedWebClientBuilder() {
//		return WebClient.builder();
//	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("shippings",
						r -> r.path("/shippings/**")
								.filters(f->{
									return f.filter(filter);
								}).uri("lb://SHIPPING-SERVICE"))
				.route("orders",
						r -> r.path("/orders/**")
								.filters(f->{
									return f.filter(filter);
								}).uri("lb://ORDER-SERVICE"))

				.build();
	}
}
