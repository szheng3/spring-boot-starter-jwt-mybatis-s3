package com.digitalsoftware.accounting.mapper.DAO;

import com.digitalsoftware.accounting.domain.generated.User;
import com.digitalsoftware.accounting.domain.models.UserRole;
import com.digitalsoftware.accounting.emun.domain.RoleType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UserMapperTest {

    private static UserMapper mapper;
    @Autowired
    private UserMapper userMapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(UserMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/UserMapperTestConfiguration.xml"));
        //you can use builder.openSession(true) to commit to database
        mapper = builder.getConfiguration().getMapper(UserMapper.class, builder.openSession());
    }

    @Test
    public void selectAll() {
        List<User> users = userMapper.selectAll();
    }

    @Test
    public void testfindOneByPhone() throws FileNotFoundException {
        UserRole testUser2 = userMapper.findOneByPhone("testUser2");
        List<RoleType> roleTypes = testUser2.getRoleTypes();
        assertNotEquals(roleTypes.size(), 0);
    }

    @Test
    public void testexistsByPhone() throws FileNotFoundException {
        boolean b = userMapper.existsByPhone("testUser22");
    }
}
