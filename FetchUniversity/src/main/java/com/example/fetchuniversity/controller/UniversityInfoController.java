package com.example.fetchuniversity.controller;

import com.example.fetchuniversity.pojo.UniversityInfo;
import com.example.fetchuniversity.service.UniversityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UniversityInfoController {

    private final UniversityInfoService universityInfoService;

    @Autowired
    public UniversityInfoController(UniversityInfoService universityInfoService){
        this.universityInfoService = universityInfoService;
    }

    @RequestMapping(value="/university", params="name")
    public ResponseEntity<?> getUniversityByName(@RequestParam String name){
        List<UniversityInfo> universities = universityInfoService.getUniversityInfoByName(name);
        return new ResponseEntity<>(universities, HttpStatus.OK);
    }

    @RequestMapping(value="/university", params="country")
    public ResponseEntity<?> getUniversityByCountry(@RequestParam String country){
        List<UniversityInfo> universities = universityInfoService.getUniversityInfoByCountry(country);
        return new ResponseEntity<>(universities, HttpStatus.OK);
    }
}
