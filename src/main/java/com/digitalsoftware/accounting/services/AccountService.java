package com.digitalsoftware.accounting.services;


import com.digitalsoftware.accounting.configuration.exception.exception.UserExistsException;
import com.digitalsoftware.accounting.domain.generated.User;
import com.digitalsoftware.accounting.domain.models.Account;
import com.digitalsoftware.accounting.emun.domain.RoleType;
import com.digitalsoftware.accounting.mapper.DAO.RoleMapper;
import com.digitalsoftware.accounting.mapper.DAO.UserHasRoleMapper;
import com.digitalsoftware.accounting.mapper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountService implements UserDetailsService, RegisterService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserHasRoleMapper userHasRoleMapper;

    private final RoleMapper roleMapper;


    @Autowired
    public AccountService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserHasRoleMapper userHasRoleMapper, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userHasRoleMapper = userHasRoleMapper;
        this.roleMapper = roleMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Account> account = Optional.ofNullable(userRepository.findByPhone(s));
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
        }
    }

    private Account findAccountByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = Optional.ofNullable(userRepository.findByPhone(username));
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
    }

    @Override
    public void register(User record) {
        if (userRepository.existsByPhone(record.getPhone())) {
            throw new UserExistsException();
        }
        userRepository.save(record);
        userHasRoleMapper.insertByUserIdAndRole(record.getId(), RoleType.ROLE_USER.getCode());



    }


    //    @Transactional // To successfully remove the date @Transactional annotation must be added
    public void removeAuthenticatedAccount() throws UsernameNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account acct = findAccountByUsername(username);
        userRepository.delete(acct.getId());
    }
}
