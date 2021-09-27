package zura.pustota.simplebank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import zura.pustota.simplebank.entity.Account;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumberEquals(String accountNumber);
}
