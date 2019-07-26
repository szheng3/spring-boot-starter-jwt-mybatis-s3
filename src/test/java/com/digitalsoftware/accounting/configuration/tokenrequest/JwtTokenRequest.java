package com.digitalsoftware.accounting.configuration.tokenrequest;

import com.digitalsoftware.accounting.emun.domain.GrantType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenRequest {

    protected String username;
    protected String password;
    protected GrantType grantType;
}
