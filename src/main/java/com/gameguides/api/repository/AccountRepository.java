package com.gameguides.api.repository;

import com.gameguides.api.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findOneByUuid(UUID uuid);
}
