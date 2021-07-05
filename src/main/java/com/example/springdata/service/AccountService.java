package com.example.springdata.service;

import com.example.springdata.entity.Account;
import com.example.springdata.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account creatUserAccount(User user, Account account);
    void withdrawMoney(BigDecimal amount, Long accountId);
    void depositMoney(BigDecimal amount, Long accountId);
    void transferMoney(BigDecimal amount, Long fromAccountId, Long toAccountId);

    List<Account> getAllAccounts();
}
