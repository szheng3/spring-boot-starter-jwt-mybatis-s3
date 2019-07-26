package com.digitalsoftware.accounting.domain.generated.doubleKey;

import com.digitalsoftware.accounting.domain.RootEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`role_has_menu`")
public class RoleHasMenu extends RootEntity {
    @Id
    @Column(name = "`role_id`")
    private Integer roleId;

    @Id
    @Column(name = "`menu_id_menu`")
    private Integer menuIdMenu;

    public RoleHasMenu(Integer roleId, Integer menuIdMenu) {
        this.roleId = roleId;
        this.menuIdMenu = menuIdMenu;
    }

    public RoleHasMenu() {
        super();
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

    /**
     * @return menu_id_menu
     */
    public Integer getMenuIdMenu() {
        return menuIdMenu;
    }

    /**
     * @param menuIdMenu
     */
    public void setMenuIdMenu(Integer menuIdMenu) {
        this.menuIdMenu = menuIdMenu;
    }

    @Override
    public RoleHasMenu getId() {
        if (roleId == null || menuIdMenu == null) {
            return null;
        }
        return new RoleHasMenu(roleId, menuIdMenu);
    }
}