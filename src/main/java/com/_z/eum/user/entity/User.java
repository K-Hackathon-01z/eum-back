package com._z.eum.user.entity;

import io.swagger.v3.oas.annotations.info.Contact;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String name;

    private int age;

    private String gender;

    private String address;

    public User(){}

}
