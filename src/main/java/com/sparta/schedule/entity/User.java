package com.sparta.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class User extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String userName;

    private String password;

    private String role;


    public User(String nickname, String userName, String password, String role) {
        this.nickname = nickname;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

}
