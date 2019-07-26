package com.digitalsoftware.accounting.mapper;

import com.digitalsoftware.accounting.domain.RootEntity;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import tk.mybatis.mapper.common.Mapper;

import java.util.Optional;

public abstract class RootRepository<T extends RootEntity, I> implements CacheWebflux<T, I> {

    @Autowired
    protected Mapper<T> mapper;


    public Mono<T> findMono(I Id) {

        return Mono.justOrEmpty(findOne(Id));
    }


    protected Integer genericSave(T record) {

        if (record.getId() != null) {

            return mapper.updateByPrimaryKeySelective(record);
        } else {

            return mapper.insertSelective(record);
        }


    }

    protected Integer genericDelete(I Id) {
        return mapper.deleteByPrimaryKey(Id);
    }

    protected Optional<T> genericFindOne(I Id) {
        return Optional.ofNullable(mapper.selectByPrimaryKey(Id));
    }


}
