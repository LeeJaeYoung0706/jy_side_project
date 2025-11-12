package com.msa.accounts.repository;


import com.msa.accounts.entity.accounts.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    void deleteByCustomerId(Long customerId);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from Accounts a where a.customerId = :customerId")
    void deleteByCustomerId2(@Param("customerId") Long customerId);
}
