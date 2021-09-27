package zura.pustota.simplebank.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zura.pustota.simplebank.dto.TransferBalanceRequest;
import zura.pustota.simplebank.entity.Account;
import zura.pustota.simplebank.entity.Transaction;
import zura.pustota.simplebank.service.AccountService;

import java.awt.*;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountController {
    AccountService accountService;

    @PostMapping(value = "/accounts", produces = APPLICATION_JSON_VALUE, consumes =APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/accounts").toUriString());
        accountService.save(account);
        return ResponseEntity.created(uri).body(account);

    }
    @GetMapping(value ="/accounts", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Account>> getAllAccounts(){
        return ResponseEntity.ok().body(accountService.findAllAccounts());
    }
    @PostMapping(value = "/transfer", consumes =APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> transferMoney(@RequestBody TransferBalanceRequest transferBalanceRequest){
        return ResponseEntity.ok().body(accountService.transferMoney(transferBalanceRequest));
    }
    @GetMapping(value = "/transactions", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Transaction>> findALlTransactions(){
        return ResponseEntity.ok().body(accountService.findAllTransactions());
    }


}
