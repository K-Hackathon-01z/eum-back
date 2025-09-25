package com._z.eum.user.entity;

import io.swagger.v3.oas.annotations.info.Contact;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String name;

    private int age;

    private String address;

    private Boolean isVerified;

    protected User (){}

}
