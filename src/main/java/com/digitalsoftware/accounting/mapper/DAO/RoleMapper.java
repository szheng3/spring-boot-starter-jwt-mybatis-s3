package com.digitalsoftware.accounting.mapper.DAO;

import com.digitalsoftware.accounting.domain.generated.Role;
import com.digitalsoftware.accounting.emun.domain.RoleType;
import com.digitalsoftware.accounting.emun.typeHandler.RoleTypeByteHandler;
import com.digitalsoftware.accounting.mapper.MyMapper;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RoleMapper extends MyMapper<Role> {


    @Select("select * from role join user_has_role role2 on role.id = role2.role_id where user_id=#{userId}")
    @ConstructorArgs({
        @Arg(column = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
        @Arg(column = "role", javaType = RoleType.class, typeHandler = RoleTypeByteHandler.class, jdbcType = JdbcType.TINYINT)
    })
    List<Role> findByUserId(@Param("userId") Integer userId);


    @Select("select * from role where role=#{roleType,jdbcType=TINYINT,typeHandler=com.digitalsoftware.accounting.emun.typeHandler.RoleTypeByteHandler}")
    @ConstructorArgs({
        @Arg(column = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
        @Arg(column = "role", javaType = RoleType.class, typeHandler = RoleTypeByteHandler.class, jdbcType = JdbcType.TINYINT)
    })
    Role findByRole(@Param("roleType") RoleType roleType);
}