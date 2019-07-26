package com.digitalsoftware.accounting.mapper.repositories;

import com.digitalsoftware.accounting.domain.generated.unique.IpDetails;
import com.digitalsoftware.accounting.mapper.RootRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class IpDetailsRepositoryImpl extends RootRepository<IpDetails, String> implements IpDetailsRepository {
    @Override
    public Optional<IpDetails> findOne(String id) {
        return genericFindOne(id);
    }

    @Override
    public Integer save(IpDetails record) {
        if (findOne(record.getIp()).isPresent()) {

            return mapper.insertSelective(record);

        } else {
            return genericSave(record);

        }
    }

    @Override
    public Integer delete(String id) {
        return genericDelete(id);
    }

}
