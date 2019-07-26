package com.digitalsoftware.accounting.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceConfig extends ResourceServerConfigurerAdapter {


    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    // The DefaultTokenServices bean provided at the AuthorizationConfig
    @Autowired
    private DefaultTokenServices tokenServices;

    // The TokenStore bean provided at the AuthorizationConfig
    @Autowired
    private TokenStore tokenStore;

    // To allow the rResourceServerConfigurerAdapter to understand the token,
    // it must share the same characteristics with AuthorizationServerConfigurerAdapter.
    // So, we must wire it up the beans in the ResourceServerSecurityConfigurer.
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
            .resourceId(resourceId)
            .tokenServices(tokenServices)
            .tokenStore(tokenStore);
    }


    //2.0
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        Duration expiration = Duration.ofSeconds(60 * 60 * 24);
//        return RedisCacheManager.builder(redisConnectionFactory)
//            .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(expiration)).build();
//    }

//    @Bean
//    public ReactiveRedisTemplate<String, Object> reactiveJsonObjectRedisTemplate(
//        ReactiveRedisConnectionFactory connectionFactory) {
//
//        RedisSerializationContext.RedisSerializationContextBuilder<String, Object> builder = RedisSerializationContext
//            .newSerializationContext(new StringRedisSerializer());
//
//        RedisSerializationContext<String, Object> serializationContext = builder
//            .value(new GenericJackson2JsonRedisSerializer("_type")).build();
//
//        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
//    }

//
//    @Bean
//    Jackson2JsonEncoder jackson2JsonEncoder(ObjectMapper mapper){
//        return new Jackson2JsonEncoder(mapper);
//    }
//
//    @Bean
//    Jackson2JsonDecoder jackson2JsonDecoder(ObjectMapper mapper){
//        return new Jackson2JsonDecoder(mapper);
//    }
//
//    @Bean
//    WebFluxConfigurer webFluxConfigurer(Jackson2JsonEncoder encoder, Jackson2JsonDecoder decoder){
//        return new WebFluxConfigurer() {
//            @Override
//            public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
//                configurer.defaultCodecs().jackson2JsonEncoder(encoder);
//                configurer.defaultCodecs().jackson2JsonDecoder(decoder);
//            }
//        };
//    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http

//                .requestMatcher(new OAuthRequestedMatcher())
            .csrf().disable()
//                .anonymous().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            // when restricting access to 'Roles' you must remove the "ROLE_" part role
            // for "ROLE_USER" use only "USER"
            .antMatchers("/api/image/**").permitAll()
            .antMatchers("/api/oauth/token").permitAll()
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/api/registerUser").permitAll()

//            .antMatchers("/api/uploadImage").permitAll()
            .antMatchers("/api/hello").access("hasAnyRole('USER')")
            .antMatchers("/api/me").hasAnyRole("USER", "ADMIN")
            .antMatchers("/api/admin/**").hasRole("ADMIN")
            // use the full name when specifying authority access
//            .antMatchers("/api/registerUser").hasAuthority("ROLE_REGISTER")
            // restricting all access to /api/** to authenticated users
            .antMatchers("/api/**").authenticated();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control", "Content-Type", "Authorization"));
        configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}

