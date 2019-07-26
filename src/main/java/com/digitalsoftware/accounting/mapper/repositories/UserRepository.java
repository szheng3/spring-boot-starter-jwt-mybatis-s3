package com.digitalsoftware.accounting.mapper.repositories;

import com.digitalsoftware.accounting.domain.generated.User;
import com.digitalsoftware.accounting.domain.models.Account;
import com.digitalsoftware.accounting.mapper.CacheWebflux;

public interface UserRepository extends CacheWebflux<User, Integer> {
     Account findByPhone(String phone);

     Account findAccountById(Integer id);

     boolean existsByPhone(String phone);

}
