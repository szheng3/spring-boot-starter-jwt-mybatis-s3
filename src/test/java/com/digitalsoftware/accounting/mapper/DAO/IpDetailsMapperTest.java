package com.digitalsoftware.accounting.mapper.DAO;

import com.digitalsoftware.accounting.domain.generated.unique.IpDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class IpDetailsMapperTest {


    @Autowired
    private IpDetailsMapper ipDetailsMapper;

    @Test
    public void testupdateByPrimaryKeySelective() throws FileNotFoundException {
        IpDetails ipDetails = new IpDetails();
        ipDetails.setIp("127.0.0.1");
        ipDetails.setCity("Unknown");
        ipDetailsMapper.updateByPrimaryKeySelective(ipDetails);

    }
}
