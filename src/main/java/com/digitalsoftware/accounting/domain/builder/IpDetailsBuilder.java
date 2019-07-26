package com.digitalsoftware.accounting.domain.builder;

import com.digitalsoftware.accounting.domain.generated.unique.IpDetails;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;

public final class IpDetailsBuilder {
    private String ip;
    private String country = "Unknown";
    private String subdivision;
    private String city;
    private String latitude;
    private String longitude;
    private DatabaseReader databaseReader;
    private HttpServletRequest request;

    private IpDetailsBuilder() {
    }

    public static IpDetailsBuilder builder() {
        return new IpDetailsBuilder();
    }

    public IpDetailsBuilder withIp(String ip) {
        this.ip = ip;
        return this;
    }

    public IpDetailsBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public IpDetailsBuilder withSubdivision(String subdivision) {
        this.subdivision = subdivision;
        return this;
    }

    public IpDetailsBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public IpDetailsBuilder withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public IpDetailsBuilder withLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public IpDetails build() throws IOException {
        IpDetails ipDetails = new IpDetails();
        ip = request.getRemoteAddr();

        InetAddress ipAddress = InetAddress.getByName(ip);
        ipDetails.setIp(ip);

        CityResponse countryResponse = null;
        try {
            countryResponse = databaseReader.city(ipAddress);

            country = countryResponse.getCountry().getName();
            subdivision = countryResponse.getMostSpecificSubdivision().getName();
            city = countryResponse.getCity().getName();
            latitude = countryResponse.getLocation().getLatitude().toString();
            longitude = countryResponse.getLocation().getLongitude().toString();


        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }
        ipDetails.setCountry(country);
        ipDetails.setSubdivision(subdivision);
        ipDetails.setCity(city);
        ipDetails.setLatitude(latitude);
        ipDetails.setLongitude(longitude);
        return ipDetails;
    }

    public IpDetailsBuilder withDB(DatabaseReader databaseReader) {
        this.databaseReader = databaseReader;
        return this;
    }

    public IpDetailsBuilder withRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }
}
