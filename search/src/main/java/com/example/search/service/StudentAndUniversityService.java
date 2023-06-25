package com.example.search.service;

import com.example.search.pojo.Student;
import com.example.search.pojo.StudentAndUniversity;
import com.example.search.pojo.University;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentAndUniversityService {

    StudentAndUniversity getStudentByIdAndUniversityByCountry(String id, String country);

    StudentAndUniversity getStudentByIdAndUniversityByName(String id, String name);
}
