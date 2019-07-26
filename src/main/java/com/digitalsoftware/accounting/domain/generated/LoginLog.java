package com.digitalsoftware.accounting.domain.generated;

import com.digitalsoftware.accounting.domain.RootEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`login_log`")
public class LoginLog extends RootEntity {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`login_date`")
    private Date loginDate;

    @Column(name = "`ip`")
    private String ip;

    @Column(name = "`user_id`")
    private Integer userId;

    @Column(name = "`os`")
    private String os;

    @Column(name = "`brower`")
    private String brower;

    @Column(name = "`deviceId`")
    private String deviceid;

    public LoginLog(Integer id, Date loginDate, String ip, Integer userId, String os, String brower, String deviceid) {
        this.id = id;
        this.loginDate = loginDate;
        this.ip = ip;
        this.userId = userId;
        this.os = os;
        this.brower = brower;
        this.deviceid = deviceid;
    }

    public LoginLog() {
        super();
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return login_date
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * @param loginDate
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return os
     */
    public String getOs() {
        return os;
    }

    /**
     * @param os
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * @return brower
     */
    public String getBrower() {
        return brower;
    }

    /**
     * @param brower
     */
    public void setBrower(String brower) {
        this.brower = brower;
    }

    /**
     * @return deviceId
     */
    public String getDeviceid() {
        return deviceid;
    }

    /**
     * @param deviceid
     */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
}