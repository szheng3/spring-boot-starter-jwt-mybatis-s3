package com.digitalsoftware.accounting.domain.builder;

import com.digitalsoftware.accounting.domain.generated.LoginLog;

import java.util.Date;

public final class LoginLogBuilder {
    private Integer id;
    private Date loginDate;
    private String ip;
    private Integer userId;
    private String os;
    private String brower;
    private String deviceid;

    private LoginLogBuilder() {
    }

    public static LoginLogBuilder builder() {
        return new LoginLogBuilder();
    }


    public LoginLogBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public LoginLogBuilder withLoginDate(Date loginDate) {
        this.loginDate = loginDate;
        return this;
    }

    public LoginLogBuilder withIp(String ip) {
        this.ip = ip;
        return this;
    }

    public LoginLogBuilder withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public LoginLogBuilder withOs(String os) {
        this.os = os;
        return this;
    }

    public LoginLogBuilder withBrower(String brower) {
        this.brower = brower;
        return this;
    }

    public LoginLogBuilder withDeviceid(String deviceid) {
        this.deviceid = deviceid;
        return this;
    }

    public LoginLog build() {
        LoginLog loginLog = new LoginLog();
        loginLog.setId(id);
        loginLog.setLoginDate(loginDate);
        loginLog.setIp(ip);
        loginLog.setUserId(userId);
        loginLog.setOs(os);
        loginLog.setBrower(brower);
        loginLog.setDeviceid(deviceid);
        return loginLog;
    }
}
