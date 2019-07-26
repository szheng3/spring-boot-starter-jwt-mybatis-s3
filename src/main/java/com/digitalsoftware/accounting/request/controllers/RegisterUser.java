package com.digitalsoftware.accounting.request.controllers;

import com.digitalsoftware.accounting.configuration.exception.exception.UserExistsException;
import com.digitalsoftware.accounting.domain.generated.User;
import com.digitalsoftware.accounting.domain.models.Account;
import com.digitalsoftware.accounting.request.Request;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;


@Data
public class RegisterUser extends Request {


    public RegisterUser(@NotNull User user) {
        this.user = user;
    }

    @NotNull
    private User user;


    private PasswordEncoder passwordEncoder;




    @Override
    public void isValid() {


        if (user.getPassword() == null || user.getPhone() == null) {

            throw new UserExistsException("Password and phone are required");

        }

    }

    @Override
    public void unWrap(Account account) {

        user.setId(null);

        user.setNickname(user.getPhone());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

    }


    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



}
