package com.guanshan.phoenix.application.config;

import io.rancher.Rancher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class RacherConfig {

    @Value("${rancher.url}")
    private String rancherUrl;
    @Value("${rancher.username}")
    private String username;
    @Value("${rancher.password}")
    private String password;

    @Bean
    public Rancher rancher() throws MalformedURLException {
        Rancher.Config config = new Rancher.Config(new URL(rancherUrl), username, password);
        return new Rancher(config);
    }
}
