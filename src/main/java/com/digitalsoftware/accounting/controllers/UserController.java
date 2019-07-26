package com.digitalsoftware.accounting.controllers;

import com.digitalsoftware.accounting.domain.models.Account;
import com.digitalsoftware.accounting.domain.models.UserModel;
import com.digitalsoftware.accounting.mapper.repositories.UserRepository;
import com.digitalsoftware.accounting.request.controllers.RegisterUser;
import com.digitalsoftware.accounting.request.controllers.UpdateUser;
import com.digitalsoftware.accounting.response.Controller.CurrentUserResponse;
import com.digitalsoftware.accounting.response.Response;
import com.digitalsoftware.accounting.response.ResponseBuilder;
import com.digitalsoftware.accounting.services.RegisterService;
import com.digitalsoftware.accounting.util.AccountUtil;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
public class UserController {


    private final UserRepository userRepository;

    private final RegisterService registerService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, RegisterService registerService) {
        this.userRepository = userRepository;
        this.registerService = registerService;
    }


//    @Autowired
//    private TokenEndpoint tokenEndpoint;

//    @Autowired
//    private DefaultTokenServices defaultTokenServices;

    @GetMapping("/currentUser")
    public CurrentUserResponse getCurrentUser(@ApiIgnore @ApiParam(hidden = true) OAuth2Authentication oAuth2Authentication) {

        Account account = AccountUtil.unWrap(oAuth2Authentication);


        Mono<UserModel> userModelMono = UserModel
            .getBuilder()
            .withAccount(account)
            .withUser(userRepository.findMono(account.getId()))
            .build();

        return CurrentUserResponse
            .CurrentUserResponseBuilder
            .builder()
            .withUserModel(userModelMono.block())
            .build().block();

    }

    @GetMapping("/admin/user/{id}")
    public CurrentUserResponse getUser(@PathVariable("id") Integer id, OAuth2Authentication oAuth2Authentication) {

        Account account = AccountUtil.unWrap(oAuth2Authentication);


        Mono<UserModel> userModelMono = UserModel
            .getBuilder()
            .withAccount(userRepository.findAccountById(id))
            .build();

        return CurrentUserResponse
            .CurrentUserResponseBuilder
            .builder()
            .withUserModel(userModelMono.block())
            .build().block();

    }


    @PostMapping("/updateUser")
    public Response updateUser(@RequestBody UpdateUser updateUser, @ApiIgnore OAuth2Authentication oAuth2Authentication) {

        updateUser.isValidAndUnWrap(AccountUtil.unWrap(oAuth2Authentication));

        userRepository.save(updateUser.getUser());

        return ResponseBuilder.builder().build().block();
    }

    @PostMapping(value = "/registerUser")
    public Response registerUser(@RequestBody RegisterUser registerUser, @ApiIgnore OAuth2Authentication oAuth2Authentication) {

        registerUser.setPasswordEncoder(passwordEncoder);
        registerUser.isValidAndUnWrap(AccountUtil.unWrap(oAuth2Authentication));


        registerService.register(registerUser.getUser());

        return ResponseBuilder.builder().build().block();

    }

//    @PostMapping(value = "/oauth/token")
//    public Mono<OAuth2AccessToken> login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication, @RequestParam MultiValueMap<String, String> parameters) {
//
//
//        RestTemplate restTemplate = new RestTemplate();
////        tokenEndpoint.postAccessToken(principal,parameters);
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//        requestHeaders.set("Authorization", httpServletRequest.getHeader("Authorization"));
//        HttpEntity<Object> entity = new HttpEntity<>(requestHeaders);
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/oauth/token")
//            .queryParams(parameters);
//
//        ResponseEntity<OAuth2AccessToken> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, OAuth2AccessToken.class);
//
//
//        return Mono.just(exchange.getBody());
//
//
//
//    }


}
