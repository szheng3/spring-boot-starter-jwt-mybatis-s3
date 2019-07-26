package com.digitalsoftware.accounting.services.facade;

import com.digitalsoftware.accounting.domain.builder.IpDetailsBuilder;
import com.digitalsoftware.accounting.domain.builder.UserLogBuilder;
import com.digitalsoftware.accounting.domain.generated.User;
import com.digitalsoftware.accounting.domain.generated.UserLog;
import com.digitalsoftware.accounting.domain.generated.unique.IpDetails;
import com.digitalsoftware.accounting.mapper.DAO.UserMapper;
import com.digitalsoftware.accounting.mapper.repositories.IpDetailsRepository;
import com.digitalsoftware.accounting.mapper.repositories.UserLogRepository;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import reactor.core.publisher.Mono;

import javax.servlet.ServletException;
import java.io.IOException;


@Service
public class LogServiceImpl implements LogService {

    private final UserLogRepository userLogRepository;

    private final UserMapper userMapper;

    private final DatabaseReader databaseReader;

    private final IpDetailsRepository ipDetailsRepository;

    @Autowired
    public LogServiceImpl(UserLogRepository userLogRepository, UserMapper userMapper, @Qualifier("databaseReader") DatabaseReader databaseReader, IpDetailsRepository ipDetailsRepository) {
        this.userLogRepository = userLogRepository;
        this.userMapper = userMapper;
        this.databaseReader = databaseReader;
        this.ipDetailsRepository = ipDetailsRepository;
    }

    @Override
    public void save(ContentCachingRequestWrapper request, long duration, ContentCachingResponseWrapper response) throws IOException, ServletException, GeoIp2Exception {

        UserLog userLog = UserLogBuilder
            .builder()
            .withRequest(request)
            .withResponse(response)
            .withDuration(duration)
            .build();

        IpDetails ipDetails = IpDetailsBuilder
            .builder()
            .withDB(databaseReader)
            .withRequest(request)
            .build();


        if (!userLog.getIp().equalsIgnoreCase("127.0.0.1")) {

            if (response.getStatus() == 200 && request.getRequestURI().equals("/oauth/token")) {

                Mono<String> username = Mono.justOrEmpty(request.getParameter("username"));
                username.subscribe(s -> {
                    User user = userMapper.findByPhone(s);
                    userLog.setUid(user.getId());
                });


            }

            ipDetailsRepository.save(ipDetails);
            userLogRepository.save(userLog);

        }
    }

}
