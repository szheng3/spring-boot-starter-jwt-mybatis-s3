package com.digitalsoftware.accounting.domain.generated.doubleKey;

import com.digitalsoftware.accounting.domain.RootEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`user_has_role`")
public class UserHasRole extends RootEntity {
    @Id
    @Column(name = "`user_id`")
    private Integer userId;

    @Id
    @Column(name = "`role_id`")
    private Integer roleId;

    public UserHasRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserHasRole() {
        super();
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
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public UserHasRole getId() {
        if (userId == null || roleId == null) {
            return null;
        }
        return new UserHasRole(userId, roleId);
    }
}