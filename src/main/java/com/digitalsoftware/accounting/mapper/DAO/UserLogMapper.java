package com.digitalsoftware.accounting.mapper.DAO;

import com.digitalsoftware.accounting.domain.generated.UserLog;
import com.digitalsoftware.accounting.domain.models.LogDTO;
import com.digitalsoftware.accounting.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserLogMapper extends MyMapper<UserLog> {

    //    @Select("select * from user_log where  method in (#{methods})")
//    @Results(id = "logDto", value = {
//        @Result(property = "statusCode", column = "status_code"),
//    })

    List<LogDTO> findAllByMethods(@Param("methods") List<String> methods);


}
