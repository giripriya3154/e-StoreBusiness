package cs.miu.edu;

import cs.miu.edu.domain.AccountType;
import cs.miu.edu.domain.BankAccount;
import cs.miu.edu.repository.BankAccountRepo;
import cs.miu.edu.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class BankAccountServiceApplication implements CommandLineRunner {
    @Autowired
    private BankAccountRepo bankAccountRepo;

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    public void loadData() {

        List<BankAccount> bankAccounts = Arrays.asList(
                BankAccount.builder()
                        .firstName("Priya")
                        .lastName("Giri")
                        .bankAccountNumber("12345678")
                        .type(AccountType.SAVING)
                        .emailAddress("giri@gmail.com")
                        .routingNumber(2222)
                        .balance(4000.0)
                        .build(),
                BankAccount.builder()
                        .firstName("Prabhat")
                        .lastName("Gyawali")
                        .bankAccountNumber("11223344")
                        .type(AccountType.CHECKING)
                        .emailAddress("gyawali@gmail.com")
                        .routingNumber(1111)
                        .balance(5000.0)
                        .build(),
                BankAccount.builder()
                        .firstName("Sovit")
                        .lastName("Kumal")
                        .bankAccountNumber("11122233")
                        .type(AccountType.SAVING)
                        .emailAddress("sovit@gmail.com")
                        .routingNumber(3333)
                        .balance(4500.0)
                        .build());
        bankAccountRepo.saveAll(bankAccounts);
    }
}
