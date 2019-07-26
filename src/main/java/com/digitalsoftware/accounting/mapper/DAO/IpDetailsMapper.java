package com.digitalsoftware.accounting.mapper.DAO;

import com.digitalsoftware.accounting.domain.generated.unique.IpDetails;
import com.digitalsoftware.accounting.mapper.MyMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface IpDetailsMapper extends MyMapper<IpDetails> {


}
