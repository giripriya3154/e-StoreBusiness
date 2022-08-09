package cs.miu.edu;

import cs.miu.edu.domain.Paypal;
import cs.miu.edu.repository.PaypalRepo;
import cs.miu.edu.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class PaypalServiceApplication implements CommandLineRunner {
    @Autowired
    private PaypalRepo paypalRepo;

    public static void main(String[] args) {
        SpringApplication.run(PaypalServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    public void loadData() {

        List<Paypal> paypalList = Arrays.asList(Paypal.builder()
                        .firstName("Prabhat")
                        .lastName("Gyawali")
                        .emailAddress("gyawali@gmail")
                        .balance(3000.0)
                        .secureKey("2118")
                        .build(),
                Paypal.builder()
                        .firstName("Priya")
                        .lastName("Giri")
                        .emailAddress("giripriya@gmail")
                        .balance(500.0)
                        .secureKey("2399")
                        .build(),
                Paypal.builder()
                        .firstName("Ram")
                        .lastName("Thakali")
                        .emailAddress("ram@gmail")
                        .balance(1500.0)
                        .secureKey("1111")
                        .build());
        paypalRepo.saveAll(paypalList);
        System.out.println("paypal accounts has been successfully save");

    }
}
