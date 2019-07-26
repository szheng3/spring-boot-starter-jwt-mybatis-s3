package com.digitalsoftware.accounting.domain.generated;

import com.digitalsoftware.accounting.domain.RootEntity;

import javax.persistence.*;

@Table(name = "`user`")
public class User extends RootEntity {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 头像
     */
    @Column(name = "`avatar`")
    private String avatar;

    @Column(name = "`phone`")
    private String phone;

    @Column(name = "`password`")
    private String password;

    @Column(name = "`nickname`")
    private String nickname;


    @Column(name = "`details`")
    private String details;

    public User(Integer id, String avatar, String phone, String password, String nickname, Integer billingPersonId) {
        this.id = id;
        this.avatar = avatar;
        this.phone = phone;
        this.password = password;
        this.nickname = nickname;
    }

    public User(Integer id, String avatar, String phone, String password, String nickname, Integer billingPersonId, String details) {
        this.id = id;
        this.avatar = avatar;
        this.phone = phone;
        this.password = password;
        this.nickname = nickname;
        this.details = details;
    }

    public User() {
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
     * 获取头像
     *
     * @return avatar - 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    /**
     * @return details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details
     */
    public void setDetails(String details) {
        this.details = details;
    }
}
