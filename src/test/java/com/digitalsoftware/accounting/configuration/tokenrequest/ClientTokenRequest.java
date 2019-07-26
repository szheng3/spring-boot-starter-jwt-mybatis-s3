package com.digitalsoftware.accounting.configuration.tokenrequest;

import com.digitalsoftware.accounting.emun.domain.GrantType;

public class ClientTokenRequest extends JwtTokenRequest {

    public ClientTokenRequest(String username, String password, GrantType grantType) {
        super(username, password, grantType);
    }

    public ClientTokenRequest() {
        super(null, null, GrantType.CLIENT_CREDENTIALS);

    }
}
