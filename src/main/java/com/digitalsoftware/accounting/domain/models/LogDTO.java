package com.digitalsoftware.accounting.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`ip`")
    private String ip;

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

    @Column(name = "`country`")
    private String country;

    @Column(name = "`subdivision`")
    private String subdivision;

    @Column(name = "`city`")
    private String city;

    @Column(name = "`latitude`")
    private String latitude;

    @Column(name = "`longitude`")
    private String longitude;

    @Column(name = "`status_code`")
    private String statusCode;

    @Column(name = "`request_body`")
    private String requestBody;

    @Column(name = "`response_body`")
    private String responseBody;
}
