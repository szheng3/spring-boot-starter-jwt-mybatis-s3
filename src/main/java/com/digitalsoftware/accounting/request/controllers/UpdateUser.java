package com.digitalsoftware.accounting.request.controllers;

import com.digitalsoftware.accounting.domain.generated.User;
import com.digitalsoftware.accounting.domain.models.Account;
import com.digitalsoftware.accounting.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser extends Request {

    @NotNull
    private User user;

    @Override
    public void isValid() {


    }

    @Override
    public void unWrap(Account account) {

        user.setId(account.getId());

    }
}
