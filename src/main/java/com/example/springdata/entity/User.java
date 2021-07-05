package com.example.springdata.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data // Lombok
//@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private int age;
    @OneToMany(mappedBy = "user")
    private Set<Account> accounts = new HashSet<>();

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }
}