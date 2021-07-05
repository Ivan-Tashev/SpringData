package com.example.springdata.service.impl;

import com.example.springdata.dao.UserRepository;
import com.example.springdata.entity.User;
import com.example.springdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service // domain service, business layer/logic, crete bean
public class UserServiceImpl implements UserService {
    private UserRepository userRepo;

    @Autowired // dependence injection
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User register(User user) {
        return userRepo.save(user);
    }
}
