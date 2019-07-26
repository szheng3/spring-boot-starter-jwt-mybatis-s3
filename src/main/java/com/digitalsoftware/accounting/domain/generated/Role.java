package com.digitalsoftware.accounting.domain.generated;

import com.digitalsoftware.accounting.domain.RootEntity;
import com.digitalsoftware.accounting.emun.domain.RoleType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`role`")
public class Role extends RootEntity {
    @Id
    @Column(name = "`id`")
    private Integer id;

    /**
     * ROLE_USER((byte) 0), ROLE_ADMIN((byte) 1), ROLE_TRUSTED_CLIENT((byte) 2);
     */
    @Column(name = "`role`")
    private RoleType role;

    public Role(Integer id, RoleType role) {
        this.id = id;
        this.role = role;
    }

    public Role() {
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
     * 获取ROLE_USER((byte) 0), ROLE_ADMIN((byte) 1), ROLE_TRUSTED_CLIENT((byte) 2);
     *
     * @return role - ROLE_USER((byte) 0), ROLE_ADMIN((byte) 1), ROLE_TRUSTED_CLIENT((byte) 2);
     */
    public RoleType getRole() {
        return role;
    }

    /**
     * 设置ROLE_USER((byte) 0), ROLE_ADMIN((byte) 1), ROLE_TRUSTED_CLIENT((byte) 2);
     *
     * @param role ROLE_USER((byte) 0), ROLE_ADMIN((byte) 1), ROLE_TRUSTED_CLIENT((byte) 2);
     */
    public void setRole(RoleType role) {
        this.role = role;
    }
}