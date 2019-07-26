package com.digitalsoftware.accounting.domain.generated;

import com.digitalsoftware.accounting.domain.RootEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`menu`")
public class Menu extends RootEntity {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`menu_name`")
    private String menuName;

    @Column(name = "`menu_icon`")
    private String menuIcon;

    @Column(name = "`endpoint`")
    private String endpoint;

    @Column(name = "`id_parent`")
    private Integer idParent;

    public Menu(Integer id, String menuName, String menuIcon, String endpoint, Integer idParent) {
        this.id = id;
        this.menuName = menuName;
        this.menuIcon = menuIcon;
        this.endpoint = endpoint;
        this.idParent = idParent;
    }

    public Menu() {
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
     * @return menu_name
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @return menu_icon
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * @param menuIcon
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    /**
     * @return endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return id_parent
     */
    public Integer getIdParent() {
        return idParent;
    }

    /**
     * @param idParent
     */
    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }
}