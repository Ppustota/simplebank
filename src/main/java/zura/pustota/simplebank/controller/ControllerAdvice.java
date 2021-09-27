package zura.pustota.simplebank.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zura.pustota.simplebank.dto.responses.AccountNotFoundResponse;
import zura.pustota.simplebank.dto.responses.NotEnoughBalanceResponse;
import zura.pustota.simplebank.exception.AccountNotFoundException;
import zura.pustota.simplebank.exception.NotEnoughBalanceException;
import zura.pustota.simplebank.service.AccountService;

import java.sql.Timestamp;

@org.springframework.web.bind.annotation.ControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ControllerAdvice {
    AccountService accountService;

    @ExceptionHandler
    public ResponseEntity<AccountNotFoundResponse> handleAccountNotFound(AccountNotFoundException exception){
        AccountNotFoundResponse response = new AccountNotFoundResponse();
        response.setMethod(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<NotEnoughBalanceResponse> handleBalanceIssue(NotEnoughBalanceException exception){
        NotEnoughBalanceResponse response = new NotEnoughBalanceResponse();
        response.setMethod(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
