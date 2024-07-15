package com.ukg.accounts.repository;

import com.ukg.accounts.dto.AccountsDto;
import com.ukg.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    Optional<Accounts> findByAccountNumber(Long accountNumber);
}
