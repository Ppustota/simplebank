package zura.pustota.simplebank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import zura.pustota.simplebank.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    Iterable<Transaction> findByAccountNumberEquals(String accountNumber);
}
