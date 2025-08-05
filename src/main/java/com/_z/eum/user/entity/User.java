package com._z.eum.user.entity;

import com._z.eum.user.dto.request.SignupRequest;
import io.swagger.v3.oas.annotations.info.Contact;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String name;

    private int age;

    private String address;

    @Setter
    private String password;

    protected User (){}

    public User(SignupRequest signupRequest){
        this.email = signupRequest.email();
        this.address = signupRequest.address();
        this.name = signupRequest.name();
        this.age = signupRequest.age();
        this.password =signupRequest.password();
    }

}
