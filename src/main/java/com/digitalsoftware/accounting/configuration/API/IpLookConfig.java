package com.digitalsoftware.accounting.configuration.API;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;


@Configuration
public class IpLookConfig {

    @Value("${my.GeoIP2}")
    private Resource database;

    @Bean
    @Qualifier("databaseReader")
    public DatabaseReader databaseReader() throws IOException {


        return new DatabaseReader.Builder(database.getInputStream()).build();
    }
}
