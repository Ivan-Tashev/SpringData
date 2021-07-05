package com.example.springdata.service.impl;

import com.example.springdata.dao.AccountRepository;
import com.example.springdata.entity.Account;
import com.example.springdata.entity.User;
import com.example.springdata.exception.InvalidAccountOperationException;
import com.example.springdata.exception.NotExistingEntityException;
import com.example.springdata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional // each action/method commit();
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepo;

    @Autowired
    public void setAccountRepo(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Account creatUserAccount(User user, Account account) {
        account.setId(null);
        account.setUser(user);
        user.getAccounts().add(account);
        return accountRepo.save(account);
    } // commit() transaction by annotation @Transactional

    @Override
    public void withdrawMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(() -> new NotExistingEntityException(
                String.format("Entity with ID: %s does not exist.", accountId)));
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InvalidAccountOperationException(
                    String.format("Account ID: %s has not enough funds.", accountId));
        }
        account.setBalance(account.getBalance().subtract(amount));
    } // commit() transaction

    @Override
    public void depositMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(() -> new NotExistingEntityException(
                String.format("Entity with ID: %s does not exist.", accountId)));
        account.setBalance(account.getBalance().add(amount));
    }

    @Override
    public void transferMoney(BigDecimal amount, Long fromAccountId, Long toAccountId) {
        depositMoney(amount, toAccountId);
        withdrawMoney(amount, fromAccountId);
    } // commit() transaction

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
}
