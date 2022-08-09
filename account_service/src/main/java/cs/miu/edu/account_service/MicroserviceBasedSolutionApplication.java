package cs.miu.edu.account_service;

import cs.miu.edu.account_service.domain.*;
import cs.miu.edu.account_service.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class MicroserviceBasedSolutionApplication implements CommandLineRunner {
    @Autowired
    private AccountRepo accountRepo;

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceBasedSolutionApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    public void loadData() {
        List<Account> accounts = Arrays.asList(Account.builder().firstName("Ramesh")
                        .lastName("Sharma")
                        .emailAddress("admin@gmail.com")
                        .password("admin")
                        .roleType(RoleType.ADMIN)
                        .build(),
                Account.builder().firstName("Shyam")
                        .lastName("Thapa")
                        .emailAddress("shipper@gmail.com")
                        .password("shipper")
                        .roleType(RoleType.SHIPPER)
                        .build());
        accountRepo.saveAll(accounts);

    }

}
