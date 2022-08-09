package cs.miu.edu;

import cs.miu.edu.domain.CreditCard;
import cs.miu.edu.repository.CreditCardRepo;
import cs.miu.edu.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class CreditcardServiceApplication implements CommandLineRunner {
    @Autowired
    private CreditCardRepo creditCardRepo;

    public static void main(String[] args) {
        SpringApplication.run(CreditcardServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    public void loadData() {
        List<CreditCard> creditCards = Arrays.asList(CreditCard.builder()
                        .firstName("Prabhat")
                        .lastName("Gyawali")
                        .cardLimit(2000.0)
                        .ccv("4321")
                        .expiryDate(LocalDate.of(2024,11,11))
                        .balance(0.0)
                        .cardNumber("123456789").build(),
                CreditCard.builder()
                        .firstName("Priya")
                        .lastName("Giri")
                        .cardLimit(2000.0)
                        .ccv("2118")
                        .expiryDate(LocalDate.of(2024,11,11))
                        .balance(0.0)
                        .cardNumber("111111111").build());
        creditCardRepo.saveAll(creditCards);


    }
}
