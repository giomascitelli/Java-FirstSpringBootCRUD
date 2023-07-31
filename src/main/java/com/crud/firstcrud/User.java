package com.crud.firstcrud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class User {
    private int id;
    private String username;
    private int age;
    private String email;

    public User(int id, String username, int age, String email) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.email = email;
    }
}
