package com.digitalsoftware.accounting.mapper.repositories;

import com.digitalsoftware.accounting.domain.models.LogDTO;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UserLogRepositoryImplTest {


    @Autowired
    private UserLogRepository userLogRepository;

    @Test
    public void findByPage() {
        PageInfo<LogDTO> byPage = userLogRepository.findByPage(1, 10, "id desc", null);

        assertEquals(10, Objects.requireNonNull(byPage).getPageSize());
    }
}
