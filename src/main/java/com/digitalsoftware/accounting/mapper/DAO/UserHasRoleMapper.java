package com.digitalsoftware.accounting.mapper.DAO;

import com.digitalsoftware.accounting.domain.generated.doubleKey.UserHasRole;
import com.digitalsoftware.accounting.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserHasRoleMapper extends MyMapper<UserHasRole> {

    int insertByUserIdAndRole(@Param("userId") Integer userId, @Param("role") Byte role);




}