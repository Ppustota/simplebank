package zura.pustota.simplebank.service;

import zura.pustota.simplebank.dto.TransferBalanceRequest;
import zura.pustota.simplebank.entity.Account;
import zura.pustota.simplebank.entity.Transaction;

public interface AccountService {
    Account save(Account account);
    Transaction transferMoney(TransferBalanceRequest transferBalanceRequest);
    Iterable<Account> findAllAccounts();
    Iterable<Transaction> findAllTransactions();
}
