package com.digitalsoftware.accounting.request;

import com.digitalsoftware.accounting.domain.models.Account;

public abstract class Request {

    public void isValidAndUnWrap(Account account) {
        isValid();
        unWrap(account);
    }

    public abstract void isValid();

    public abstract void unWrap(Account account);

}
