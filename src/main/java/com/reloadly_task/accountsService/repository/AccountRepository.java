package com.reloadly_task.accountsService.repository;

import com.reloadly_task.accountsService.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(long id);

    BigDecimal findAccountBalanceByUserId(Long id);
}
