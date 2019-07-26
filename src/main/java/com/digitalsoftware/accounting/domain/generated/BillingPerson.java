package com.digitalsoftware.accounting.domain.generated;

import com.digitalsoftware.accounting.domain.RootEntity;
import com.digitalsoftware.accounting.emun.domain.StatusState;

import javax.persistence.*;

@Table(name = "`billing_person`")
public class BillingPerson extends RootEntity {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 0=inactive,1=active
     */
    @Column(name = "`status`")
    private StatusState status;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`cellphone`")
    private String cellphone;

    public BillingPerson(Integer id, StatusState status, String name, String cellphone) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.cellphone = cellphone;
    }

    public BillingPerson() {
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
     * 获取0=inactive,1=active
     *
     * @return status - 0=inactive,1=active
     */
    public StatusState getStatus() {
        return status;
    }

    /**
     * 设置0=inactive,1=active
     *
     * @param status 0=inactive,1=active
     */
    public void setStatus(StatusState status) {
        this.status = status;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return cellphone
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * @param cellphone
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}