package com.digitalsoftware.accounting.mapper.DAO;

import com.digitalsoftware.accounting.domain.generated.User;
import com.digitalsoftware.accounting.domain.models.UserRole;
import com.digitalsoftware.accounting.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserMapper extends MyMapper<User> {

//    @Select("select * from user where phone=#{phone}")
//    User findByPhone(@Param("phone") String phone);

    User findByPhone(@Param("phone") String phone);

    UserRole findOneByPhone(@Param("phone") String phone);


    boolean existsByPhone(@Param("phone") String phone);


}