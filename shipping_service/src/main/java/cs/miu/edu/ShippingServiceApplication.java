package cs.miu.edu;

import cs.miu.edu.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient

public class ShippingServiceApplication implements CommandLineRunner {
    @Autowired
    private ShippingService productService;

    public static void main(String[] args) {
        SpringApplication.run(ShippingServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //loadData();
    }

    public void loadData() {

    }
}
