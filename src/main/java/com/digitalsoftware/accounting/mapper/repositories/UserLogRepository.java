package com.digitalsoftware.accounting.mapper.repositories;

import com.digitalsoftware.accounting.domain.generated.UserLog;
import com.digitalsoftware.accounting.domain.models.LogDTO;
import com.digitalsoftware.accounting.mapper.CacheWebflux;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserLogRepository extends CacheWebflux<UserLog, Integer> {

    PageInfo<LogDTO> findByPage(int pageNum, int limit, String orderBy, List<String> methods);

}
