package com.digitalsoftware.accounting.mapper.repositories;

import com.digitalsoftware.accounting.domain.generated.Role;
import com.digitalsoftware.accounting.mapper.RootRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@CacheConfig(cacheNames = "Role")
public class RoleRepositoryImpl extends RootRepository<Role, Integer> implements RoleRepository {

    @Override
    @Cacheable(key = "#p0")
    public Optional<Role> findOne(Integer id) {
        return genericFindOne(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Integer save(Role record) {
        return genericSave(record);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Integer delete(Integer id) {

        return genericDelete(id);
    }
}
