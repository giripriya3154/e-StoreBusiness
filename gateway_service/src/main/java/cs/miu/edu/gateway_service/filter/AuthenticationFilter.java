package cs.miu.edu.gateway_service.filter;

import cs.miu.edu.gateway_service.GatewayServiceApplication;
import cs.miu.edu.gateway_service.dto.AccountDto;
import cs.miu.edu.gateway_service.dto.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class AuthenticationFilter implements GatewayFilter {

//    @Autowired
//    private JwtUserDetailsService jwtUserDetailsService;

//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("I am inside filter");
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

//        final List<String> apiEndpoints = List.of("/register", "/login");
//
//        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
//                .noneMatch(uri -> r.getURI().getPath().contains(uri));

//            if (!request.getHeaders().containsKey("Authorization")) {
//                ServerHttpResponse response = exchange.getResponse();
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                System.out.println("TOKEN NOT VALID");
//                return response.setComplete();
//            }

        final String token = request.getHeaders().getOrEmpty("Authorization").get(0);
        System.out.println(token);
//            try {
//                System.out.println("token " +  token);
////                jwtTokenUtil.validateToken(token);
//               // String username = jwtTokenUtil.getUsernameFromToken(token);
////                System.out.println(username);
//            } catch (Exception e) {
//                // e.printStackTrace();
//                System.out.println("TOKEN NOT VALID Bad");
//                ServerHttpResponse response = exchange.getResponse();
//                response.setStatusCode(HttpStatus.BAD_REQUEST);
//
//                return response.setComplete();

        exchange.getRequest().mutate().header("API_KEY", "shipping-service").build();

        return chain.filter(exchange);
    }
}
