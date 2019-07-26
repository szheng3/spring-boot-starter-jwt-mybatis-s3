package com.digitalsoftware.accounting.domain.builder;

import com.digitalsoftware.accounting.domain.generated.UserLog;
import com.digitalsoftware.accounting.util.AccountUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import static com.digitalsoftware.accounting.configuration.filter.StubLoggingFilter.VISIBLE_TYPES;

public final class UserLogBuilder {
    private Integer id;
    private String ip;
    private Integer uid;
    private String url;
    private String method;
    private String duration;
    private Date date;
    private String os;
    private String brower;
    private String requestBody;
    private String responseBody;

    private String statusCode;

    private UserLogBuilder() {
    }

    public static UserLogBuilder builder() {
        return new UserLogBuilder();
    }

    public UserLogBuilder withRequest(ContentCachingRequestWrapper request) {
        ip = request.getRemoteAddr();


        url = request.getRequestURI();
        method = request.getMethod();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        brower = userAgent.getBrowser().getName();
        os = userAgent.getOperatingSystem().getName();
        uid = AccountUtil.unWrap(request.getUserPrincipal()).getId();


        val content = request.getContentAsByteArray();
        if (content.length > 0) {
            this.requestBody = logContent(content, request.getContentType(), request.getCharacterEncoding());
        }


//        request.
        return this;
    }


    public UserLogBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public UserLogBuilder withIp(String ip) {
        this.ip = ip;
        return this;
    }

    public UserLogBuilder withUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public UserLogBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public UserLogBuilder withMethod(String method) {
        this.method = method;
        return this;
    }

    public UserLogBuilder withDuration(Long duration) {
        this.duration = String.valueOf(duration);
        return this;
    }

    public UserLogBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public UserLogBuilder withOs(String os) {
        this.os = os;
        return this;
    }

    public UserLogBuilder withBrower(String brower) {
        this.brower = brower;
        return this;
    }

    public UserLog build() {
        UserLog userLog = new UserLog();
        userLog.setId(id);
        userLog.setIp(ip);
        userLog.setUid(uid);
        userLog.setUrl(url);
        userLog.setMethod(method);
        userLog.setDuration(duration);
        userLog.setDate(date);
        userLog.setOs(os);
        userLog.setBrower(brower);
        userLog.setStatusCode(statusCode);
        userLog.setRequestBody(requestBody);
        userLog.setResponseBody(responseBody);
        return userLog;
    }

    private String logContent(byte[] content, String contentType, String contentEncoding) {
        val mediaType = MediaType.valueOf(contentType);
        val visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
        if (visible) {
            try {
                return new String(content, contentEncoding);
//                Stream.of(contentString.split("\r\n|\r|\n")).forEach(line -> log.info("{} {}", prefix, line));
            } catch (UnsupportedEncodingException e) {
//                log.info("{} [{} bytes content]", prefix, content.length);
            }
        }

        return null;
    }


    public UserLogBuilder withResponse(ContentCachingResponseWrapper response) throws IOException {
        statusCode = String.valueOf(response.getStatus());

        val content = response.getContentAsByteArray();
        if (content.length > 0) {
            responseBody = logContent(content, response.getContentType(), response.getCharacterEncoding());
        }
        response.copyBodyToResponse();
        return this;
    }
}
