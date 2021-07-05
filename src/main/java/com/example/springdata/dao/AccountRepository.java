package com.example.springdata.dao;

import com.example.springdata.entity.Account;
import com.example.springdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
