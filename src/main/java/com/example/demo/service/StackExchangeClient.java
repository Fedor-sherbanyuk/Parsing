package com.example.demo.service;

import com.example.demo.model.SiteDto;
import com.example.demo.model.SitesDto;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class StackExchangeClient {
    HttpClient httpClient= HttpClientBuilder.create().build();
    ClientHttpRequestFactory requestFactory=new HttpComponentsClientHttpRequestFactory(httpClient);
    private final RestTemplate restTemplate = new RestTemplate(requestFactory);
    private final static String url = "https://api.stackexchange.com/2.3/sites?page=1&pagesize=9999&filter=!FmdZhk)Fm47clKEWzoBErb3Z2S";

    public List<SiteDto> getSites() {

        try {
            SitesDto response = restTemplate.getForObject(new URI(url), SitesDto.class);
            return response.getItems();
        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }
    }
}
