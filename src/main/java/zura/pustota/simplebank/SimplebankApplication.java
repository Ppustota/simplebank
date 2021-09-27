package zura.pustota.simplebank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import zura.pustota.simplebank.entity.Account;
import zura.pustota.simplebank.service.AccountService;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
public class SimplebankApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimplebankApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(AccountService accountService){
        return args -> {
          accountService.save(new Account(0L, UUID.randomUUID().toString(), new BigDecimal(50000)));
          accountService.save(new Account(0L, UUID.randomUUID().toString(), new BigDecimal(500)));
          accountService.save(new Account(0L, UUID.randomUUID().toString(), new BigDecimal(10000)));
        };
    }

}
