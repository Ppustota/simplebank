package zura.pustota.simplebank.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zura.pustota.simplebank.entity.Account;
import zura.pustota.simplebank.service.AccountService;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {
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
}
