package com.digitalsoftware.accounting.configuration.tokenrequest;


import com.digitalsoftware.accounting.emun.domain.GrantType;

public class UserTokenRequest extends JwtTokenRequest {


    public UserTokenRequest(String username, String password, GrantType grantType) {
        super(username, password, grantType);
    }

    public UserTokenRequest() {
        super("testUser2", "password", GrantType.PASSWORD);

    }
}
