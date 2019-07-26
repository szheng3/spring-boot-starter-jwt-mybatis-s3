package com.digitalsoftware.accounting.util;

import com.digitalsoftware.accounting.domain.models.Account;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.security.Principal;

public class AccountUtil {


    public static Account unWrap(Principal principal) {

        Account account = new Account();

        if (principal instanceof OAuth2Authentication) {

            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;

            Object details = oAuth2Authentication.getDetails();

            if (details instanceof OAuth2AuthenticationDetails) {
                OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) details;

                account = Account
                    .getBuilder()
                    .withOAuth2AuthenticationDetails(oAuth2AuthenticationDetails)
                    .build();
            }
        }
        return account;
    }
}
