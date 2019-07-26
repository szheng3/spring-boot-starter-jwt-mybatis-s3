package com.digitalsoftware.accounting.mapper;

import com.digitalsoftware.accounting.domain.RootEntity;

import java.util.Optional;

public interface MyCache<T extends RootEntity, I> {
    Optional<T> findOne(I id);

    Integer save(T record);

    Integer delete(I id);

}
