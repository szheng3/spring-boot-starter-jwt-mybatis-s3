package com.digitalsoftware.accounting.mapper.DAO;

import com.digitalsoftware.accounting.domain.models.LogDTO;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;


public class UserLogMapperTest {
    private static UserLogMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(UserLogMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/UserLogMapperTestConfiguration.xml"));
        //you can use builder.openSession(true) to commit to database
        mapper = builder.getConfiguration().getMapper(UserLogMapper.class, builder.openSession());
    }

    @Test
    public void testfindAllByMethods() throws FileNotFoundException {
        List<LogDTO> allByMethods = mapper.findAllByMethods(null);
//        assertEquals("200", allByMethods.get(0).getStatusCode());
    }
}
