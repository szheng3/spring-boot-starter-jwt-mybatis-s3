package com.digitalsoftware.accounting.configuration.security;


import com.digitalsoftware.accounting.emun.domain.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.Arrays;
import java.util.Map;


@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {


    private int accessTokenValiditySeconds = 10000;
    private int refreshTokenValiditySeconds = 30000;

    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    @Value("${oauth2.clientId}")
    private String clientId;

    @Value("${oauth2.secret}")
    private String secret;
//
//    @Bean
//    RedisCacheManager cacheManager() {
//        final RedisCacheManager redisCacheManager = new RedisCacheManager(
//            new RedisTemplate());
//        redisCacheManager.setUsePrefix(true);
//        redisCacheManager.setDefaultExpiration(1);
//
//
//        return redisCacheManager;
//    }


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
        endpoints
            .authenticationManager(this.authenticationManager)
            .tokenServices(tokenServices())
            .tokenStore(tokenStore())
            .tokenEnhancer(tokenEnhancerChain)
            .accessTokenConverter(accessTokenConverter());

    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

        oauthServer
            // we're allowing access to the token only for clients with 'ROLE_TRUSTED_CLIENT' authority
            .tokenKeyAccess("hasAuthority('ROLE_TRUSTED_CLIENT')")
            .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");

    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()

            .withClient(clientId)
            .authorizedGrantTypes("client_credentials", "password", "refresh_token")
            .authorities(RoleType.ROLE_TRUSTED_CLIENT.toString(), RoleType.ROLE_REGISTER.toString())
            .scopes("read", "write")
            .resourceIds(resourceId)
            .accessTokenValiditySeconds(accessTokenValiditySeconds)
            .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
            .secret(passwordEncoder.encode(secret));
//            .and()
//            .withClient("register-app")
//            .authorizedGrantTypes("client_credentials")
//            .authorities(RoleType.ROLE_REGISTER.toString())
//            .scopes("registerUser")
//            .accessTokenValiditySeconds(accessTokenValiditySeconds)
//            .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
//            .resourceIds(resourceId)
//            .secret("secret");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());

    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory =
            new KeyStoreKeyFactory(
                new ClassPathResource("mykeys.jks"),
                "mypass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mykeys"));
//         converter.setSigningKey("123");
        converter.setAccessTokenConverter(new JwtConverter());

        return converter;
    }
//
//    @Autowired
//    private TokenBlackListService blackListService;

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        return defaultTokenServices;
    }


    public static class JwtConverter extends DefaultAccessTokenConverter {

        @Override
        public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
            OAuth2Authentication auth = super.extractAuthentication(map);
//            System.out.println(map);
            auth.setDetails(map); //this will get spring to copy JWT content into Authentication
            return auth;
        }
    }


}
