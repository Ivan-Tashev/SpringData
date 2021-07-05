package com.example.springdata.init;

import com.example.springdata.entity.Account;
import com.example.springdata.entity.User;
import com.example.springdata.exception.InvalidAccountOperationException;
import com.example.springdata.exception.NotExistingEntityException;
import com.example.springdata.service.AccountService;
import com.example.springdata.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component // managed Bean with is autowire
@Slf4j // catch exceptions
public class ConsoleRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @Override

    public void run(String... args) throws Exception {
        User user1 = new User("John Smith", 40);
        Account account1 = new Account(new BigDecimal(1500));

        userService.register(user1);
        accountService.creatUserAccount(user1, account1);
        // deposit & withdraw
        try {
            accountService.withdrawMoney(new BigDecimal(1000), account1.getId());
            accountService.depositMoney(new BigDecimal(200), account1.getId());
            accountService.getAllAccounts().forEach(System.out::println);
        } catch (NotExistingEntityException | InvalidAccountOperationException ex) {
            log.error(String.format("Error executing operation for account: %s: %s", account1, ex.getMessage()));
        }

        // transfer
        User user2 = new User("Anne Nikol", 33);
        Account account2 = new Account(new BigDecimal(1000));

        userService.register(user2);
        accountService.creatUserAccount(user2, account2);
        try {
            accountService.transferMoney(new BigDecimal(100), account1.getId(), account2.getId());
            accountService.getAllAccounts().forEach(System.out::println);
        } catch (NotExistingEntityException | InvalidAccountOperationException ex) {
            log.error(String.format("Error executing operation for account: %s: %s", account1, ex.getMessage()));
        }
    }
}
