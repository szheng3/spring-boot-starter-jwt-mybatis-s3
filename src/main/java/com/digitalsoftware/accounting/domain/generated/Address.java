package com.digitalsoftware.accounting.domain.generated;

import com.digitalsoftware.accounting.domain.RootEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`address`")
public class Address extends RootEntity {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`street`")
    private String street;

    @Column(name = "`town`")
    private String town;

    @Column(name = "`city`")
    private String city;

    @Column(name = "`provience`")
    private String provience;

    @Column(name = "`zipcode`")
    private String zipcode;

    public Address(Integer id, String street, String town, String city, String provience, String zipcode) {
        this.id = id;
        this.street = street;
        this.town = town;
        this.city = city;
        this.provience = provience;
        this.zipcode = zipcode;
    }

    public Address() {
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
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return town
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return provience
     */
    public String getProvience() {
        return provience;
    }

    /**
     * @param provience
     */
    public void setProvience(String provience) {
        this.provience = provience;
    }

    /**
     * @return zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}