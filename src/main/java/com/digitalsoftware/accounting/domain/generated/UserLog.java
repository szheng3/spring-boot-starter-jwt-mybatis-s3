package com.digitalsoftware.accounting.domain.generated;

import com.digitalsoftware.accounting.domain.RootEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`user_log`")
public class UserLog extends RootEntity {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`uid`")
    private Integer uid;

    @Column(name = "`url`")
    private String url;

    @Column(name = "`method`")
    private String method;

    @Column(name = "`duration`")
    private String duration;

    @Column(name = "`date`")
    private Date date;

    @Column(name = "`os`")
    private String os;

    @Column(name = "`brower`")
    private String brower;

    @Column(name = "`ip`")
    private String ip;

    @Column(name = "`status_code`")
    private String statusCode;

    @Column(name = "`request_body`")
    private String requestBody;

    @Column(name = "`response_body`")
    private String responseBody;

    public UserLog(Integer id, Integer uid, String url, String method, String duration, Date date, String os, String brower, String ip, String statusCode) {
        this.id = id;
        this.uid = uid;
        this.url = url;
        this.method = method;
        this.duration = duration;
        this.date = date;
        this.os = os;
        this.brower = brower;
        this.ip = ip;
        this.statusCode = statusCode;
    }

    public UserLog(Integer id, Integer uid, String url, String method, String duration, Date date, String os, String brower, String ip, String statusCode, String requestBody, String responseBody) {
        this.id = id;
        this.uid = uid;
        this.url = url;
        this.method = method;
        this.duration = duration;
        this.date = date;
        this.os = os;
        this.brower = brower;
        this.ip = ip;
        this.statusCode = statusCode;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public UserLog() {
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
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
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
     * @return status_code
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return request_body
     */
    public String getRequestBody() {
        return requestBody;
    }

    /**
     * @param requestBody
     */
    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    /**
     * @return response_body
     */
    public String getResponseBody() {
        return responseBody;
    }

    /**
     * @param responseBody
     */
    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}