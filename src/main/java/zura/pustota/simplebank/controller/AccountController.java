package zura.pustota.simplebank.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zura.pustota.simplebank.dto.TransferBalanceRequest;
import zura.pustota.simplebank.entity.Transaction;
import zura.pustota.simplebank.service.AccountService;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountController {
    AccountService accountService;

    @PostMapping(value = "/transfer", consumes =APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> transferMoney(@RequestBody TransferBalanceRequest transferBalanceRequest){
        return ResponseEntity.ok().body(accountService.transferMoney(transferBalanceRequest));
    }
    @GetMapping(value = "/transactions", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Transaction>> findALlTransactions(){
        return ResponseEntity.ok().body(accountService.findAllTransactions());
    }


}
