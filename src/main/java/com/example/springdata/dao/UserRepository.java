package com.example.springdata.dao;

import com.example.springdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // dao/repo
public interface UserRepository extends JpaRepository<User, Long> {
}
