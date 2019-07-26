package com.digitalsoftware.accounting.services.facade;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.ServletException;
import java.io.IOException;

public interface LogService {


    void save(ContentCachingRequestWrapper request, long duration, ContentCachingResponseWrapper response) throws IOException, ServletException, GeoIp2Exception;
}
