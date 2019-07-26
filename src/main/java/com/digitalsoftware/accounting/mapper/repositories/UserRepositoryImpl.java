package com.digitalsoftware.accounting.mapper.repositories;


import com.digitalsoftware.accounting.configuration.exception.exception.ResourceMissingException;
import com.digitalsoftware.accounting.domain.generated.Role;
import com.digitalsoftware.accounting.domain.generated.User;
import com.digitalsoftware.accounting.domain.models.Account;
import com.digitalsoftware.accounting.domain.models.UserRole;
import com.digitalsoftware.accounting.mapper.DAO.RoleMapper;
import com.digitalsoftware.accounting.mapper.DAO.UserMapper;
import com.digitalsoftware.accounting.mapper.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@CacheConfig(cacheNames = "User")
@Repository
public class UserRepositoryImpl extends RootRepository<User, Integer> implements UserRepository {


    private final UserMapper userMapper;

    private final RoleMapper roleMapper;


    @Autowired
    public UserRepositoryImpl(UserMapper userMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    public Account findByPhone(String phone) {


        UserRole userRole = userMapper.findOneByPhone(phone);
//        User account = userMapper.findByPhone(phone);

//        List<Role> roleTypes = roleMapper.findByUserId(account.getId());
        return Account.getBuilder().withUserRole(userRole).build();

    }


    @Cacheable(key = "#p0")
    @Override
    public Optional<User> findOne(Integer id) {
        return genericFindOne(id);
    }

    @Override
    public Account findAccountById(Integer id) {
        User account = findOne(id).orElseThrow(ResourceMissingException::new);
        List<Role> roleTypes = roleMapper.findByUserId(account.getId());
        return Account.getBuilder().withUser(account).withRole(roleTypes).build();
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userMapper.existsByPhone(phone);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Integer save(User record) {
        return genericSave(record);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Integer delete(Integer id) {

        return genericDelete(id);
    }
}
