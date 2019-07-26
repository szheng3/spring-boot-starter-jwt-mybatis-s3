package com.digitalsoftware.accounting.mapper;

import com.digitalsoftware.accounting.domain.RootEntity;

public interface CacheWebflux<T extends RootEntity, I> extends Webflux<T, I>, MyCache<T, I> {
}
