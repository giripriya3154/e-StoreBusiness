package cs.miu.edu.account_service.clients.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class TransactionFeignConfig {
    @Value("${transaction.service.key}")
    public String serviceKey;

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            System.out.println(serviceKey);
            requestTemplate.header("API_KEY", serviceKey);
        };
    }
}
