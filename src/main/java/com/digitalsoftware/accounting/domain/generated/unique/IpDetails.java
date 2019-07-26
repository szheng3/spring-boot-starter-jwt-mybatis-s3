package com.digitalsoftware.accounting.domain.generated.unique;

import com.digitalsoftware.accounting.domain.RootEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`ip_details`")
public class IpDetails extends RootEntity {
    @Id
    @Column(name = "`ip`")
    private String ip;

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

    public IpDetails(String ip, String country, String subdivision, String city, String latitude, String longitude) {
        this.ip = ip;
        this.country = country;
        this.subdivision = subdivision;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public IpDetails() {
        super();
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
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return subdivision
     */
    public String getSubdivision() {
        return subdivision;
    }

    /**
     * @param subdivision
     */
    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
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
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public Object getId() {
        return ip;
    }
}