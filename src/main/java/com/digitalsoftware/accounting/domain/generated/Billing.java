package com.digitalsoftware.accounting.domain.generated;

import com.digitalsoftware.accounting.domain.RootEntity;
import com.digitalsoftware.accounting.emun.domain.AmountType;
import com.digitalsoftware.accounting.emun.domain.StatusState;

import javax.persistence.*;
import java.util.Date;

@Table(name = "`billing`")
public class Billing extends RootEntity {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`user_id`")
    private Integer userId;

    @Column(name = "`billing_person_id`")
    private Integer billingPersonId;

    /**
     * inactive=0,active=1
     */
    @Column(name = "`status`")
    private StatusState status;

    @Column(name = "`from_date`")
    private Date fromDate;

    @Column(name = "`to_date`")
    private Date toDate;

    @Column(name = "`amount`")
    private Float amount;

    /**
     * 0=给1=收
     */
    @Column(name = "`amount_type`")
    private AmountType amountType;

    @Column(name = "`billing_rate`")
    private Float billingRate;

    /**
     * 0借款, 1日常记录
     */
    @Column(name = "`billing_type`")
    private Boolean billingType;

    @Column(name = "`title`")
    private String title;

    @Column(name = "`record_date`")
    private Date recordDate;

    @Column(name = "`description`")
    private String description;

    public Billing(Integer id, Integer userId, Integer billingPersonId, StatusState status, Date fromDate, Date toDate, Float amount, AmountType amountType, Float billingRate, Boolean billingType, String title, Date recordDate) {
        this.id = id;
        this.userId = userId;
        this.billingPersonId = billingPersonId;
        this.status = status;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.amount = amount;
        this.amountType = amountType;
        this.billingRate = billingRate;
        this.billingType = billingType;
        this.title = title;
        this.recordDate = recordDate;
    }

    public Billing(Integer id, Integer userId, Integer billingPersonId, StatusState status, Date fromDate, Date toDate, Float amount, AmountType amountType, Float billingRate, Boolean billingType, String title, Date recordDate, String description) {
        this.id = id;
        this.userId = userId;
        this.billingPersonId = billingPersonId;
        this.status = status;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.amount = amount;
        this.amountType = amountType;
        this.billingRate = billingRate;
        this.billingType = billingType;
        this.title = title;
        this.recordDate = recordDate;
        this.description = description;
    }

    public Billing() {
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
     * @return billing_person_id
     */
    public Integer getBillingPersonId() {
        return billingPersonId;
    }

    /**
     * @param billingPersonId
     */
    public void setBillingPersonId(Integer billingPersonId) {
        this.billingPersonId = billingPersonId;
    }

    /**
     * 获取inactive=0,active=1
     *
     * @return status - inactive=0,active=1
     */
    public StatusState getStatus() {
        return status;
    }

    /**
     * 设置inactive=0,active=1
     *
     * @param status inactive=0,active=1
     */
    public void setStatus(StatusState status) {
        this.status = status;
    }

    /**
     * @return from_date
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return to_date
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * @return amount
     */
    public Float getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * 获取0=给1=收
     *
     * @return amount_type - 0=给1=收
     */
    public AmountType getAmountType() {
        return amountType;
    }

    /**
     * 设置0=给1=收
     *
     * @param amountType 0=给1=收
     */
    public void setAmountType(AmountType amountType) {
        this.amountType = amountType;
    }

    /**
     * @return billing_rate
     */
    public Float getBillingRate() {
        return billingRate;
    }

    /**
     * @param billingRate
     */
    public void setBillingRate(Float billingRate) {
        this.billingRate = billingRate;
    }

    /**
     * 获取0借款, 1日常记录
     *
     * @return billing_type - 0借款, 1日常记录
     */
    public Boolean getBillingType() {
        return billingType;
    }

    /**
     * 设置0借款, 1日常记录
     *
     * @param billingType 0借款, 1日常记录
     */
    public void setBillingType(Boolean billingType) {
        this.billingType = billingType;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return record_date
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * @param recordDate
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}