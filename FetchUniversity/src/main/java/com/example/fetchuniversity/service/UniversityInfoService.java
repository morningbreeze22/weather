package com.example.fetchuniversity.service;

import com.example.fetchuniversity.pojo.UniversityInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UniversityInfoService {
    List<UniversityInfo> getUniversityInfoByName(String name);

    List<UniversityInfo> getUniversityInfoByCountry(String country);
}
