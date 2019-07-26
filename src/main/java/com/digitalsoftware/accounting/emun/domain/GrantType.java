package com.digitalsoftware.accounting.emun.domain;

public enum GrantType {
    PASSWORD("password"),
    CLIENT_CREDENTIALS("client_credentials");

    private final String code;

    GrantType(String password) {
        this.code = password;

    }

    public String getCode() {
        return this.code;
    }

}
