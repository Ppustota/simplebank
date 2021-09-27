package zura.pustota.simplebank.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zura.pustota.simplebank.dto.TransferBalanceRequest;
import zura.pustota.simplebank.entity.Account;
import zura.pustota.simplebank.entity.Transaction;
import zura.pustota.simplebank.exception.AccountNotFoundException;
import zura.pustota.simplebank.exception.NotEnoughBalanceException;
import zura.pustota.simplebank.repo.AccountRepo;
import zura.pustota.simplebank.repo.TransactionRepo;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Optional;

@Service @Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepo accountRepo;
    TransactionRepo transactionRepo;
    @Override
    public Account save(Account account) {
        account.setAccountId(0L);
        accountRepo.save(account);
        log.info("Account saved: {}", account);
        return account;
    }

    @Override
    @Transactional
    public Transaction transferMoney(TransferBalanceRequest transferBalanceRequest) {
        String fromAccountNumber = transferBalanceRequest.getFromAccountNumber();
        String toAccountNumber = transferBalanceRequest.getToAccountNumber();
        BigDecimal amount = transferBalanceRequest.getAmount();
        Account fromAccount = accountRepo.findByAccountNumberEquals(fromAccountNumber)
                .orElseThrow(()->new AccountNotFoundException("Account Not Found"));
        Account toAccount = accountRepo.findByAccountNumberEquals(toAccountNumber)
                .orElseThrow(()->new AccountNotFoundException("Account Not Found"));
        if(fromAccount.getCurrentBalance().compareTo(amount)<0)
        throw new NotEnoughBalanceException("You don't have enough balance");
        fromAccount.setCurrentBalance(fromAccount.getCurrentBalance().subtract(amount));
        accountRepo.save(fromAccount);
        toAccount.setCurrentBalance(toAccount.getCurrentBalance().add(amount));
        accountRepo.save(toAccount);
        Transaction transaction = new Transaction(0L, fromAccountNumber, amount, new Timestamp(System.currentTimeMillis()));
        transactionRepo.save(transaction);
        return transaction;
    }

    @Override
    public Iterable<Account> findAllAccounts() {
        log.info("All Account retrieved");
        return accountRepo.findAll();

    }

    @Override
    public Iterable<Transaction> findAllTransactions() {
        return transactionRepo.findAll();
    }
}
