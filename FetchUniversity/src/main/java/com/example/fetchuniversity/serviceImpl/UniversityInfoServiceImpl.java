package com.example.fetchuniversity.serviceImpl;

import com.example.fetchuniversity.pojo.UniversityInfo;
import com.example.fetchuniversity.service.UniversityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UniversityInfoServiceImpl implements UniversityInfoService {

    private final RestTemplate restTemplate;

    @Autowired
    public UniversityInfoServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public List<UniversityInfo>  getUniversityInfoByName(String name) {
        String url = "http://universities.hipolabs.com/search?name="+name;
        List<UniversityInfo>  response = Arrays.asList(restTemplate.getForObject(url,UniversityInfo[].class));
        return response;
    }

    @Override
    public List<UniversityInfo>  getUniversityInfoByCountry(String country) {
        String url = "http://universities.hipolabs.com/search?country="+country;
        List<UniversityInfo> response = Arrays.asList(restTemplate.getForObject(url,UniversityInfo[].class));
        return response;

    }
}
