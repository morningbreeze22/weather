package com.example.search.serviceImpl;

import com.example.search.config.EndPointConfig;
import com.example.search.pojo.Student;
import com.example.search.pojo.StudentAndUniversity;
import com.example.search.pojo.University;
import com.example.search.service.StudentAndUniversityService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class StudentAndUniversityServiceImpl implements StudentAndUniversityService {

    private final RestTemplate restTemplate;


    public StudentAndUniversityServiceImpl(RestTemplate getRestTemplate) {
        this.restTemplate = getRestTemplate;
    }

    @Override
    public StudentAndUniversity getStudentByIdAndUniversityByCountry(String id, String country){
        CompletableFuture<Student> fetchStudent = CompletableFuture.supplyAsync(()->{
            try{
                Student student = restTemplate.getForObject(EndPointConfig.queryStudentById+id, Student.class);
                return student;
            } catch (HttpClientErrorException e){
                return null;
            }

        });
        CompletableFuture<List<University>> fetchUniversity = CompletableFuture.supplyAsync(()->{
            try{
                List<University> universities = Arrays.asList(restTemplate.getForObject(EndPointConfig.queryUniversityByCountry+country, University[].class));
                return universities;
            } catch (HttpClientErrorException e){
                return null;
            }
        });
        CompletableFuture<Void> combined = CompletableFuture.allOf(fetchStudent,fetchUniversity);
        combined.join();
        StudentAndUniversity studentAndUniversity;
        try {
            Student studentFound = fetchStudent.get();
            List<University> universitiesFound = fetchUniversity.get();
            studentAndUniversity = new StudentAndUniversity(studentFound,universitiesFound);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return studentAndUniversity;
    }

    public StudentAndUniversity getStudentByIdAndUniversityByName(String id, String name){
        CompletableFuture<Student> fetchStudent = CompletableFuture.supplyAsync(()->{
            try{
                Student student = restTemplate.getForObject(EndPointConfig.queryStudentById+id, Student.class);
                return student;
            } catch (HttpClientErrorException e){
                return null;
            }
        });
        CompletableFuture<List<University>> fetchUniversity = CompletableFuture.supplyAsync(()->{
            try{
                List<University> universities = Arrays.asList(restTemplate.getForObject(EndPointConfig.queryUniversityByName+name, University[].class));
                return universities;
            } catch (HttpClientErrorException e){
                return null;
            }
        });
        CompletableFuture<Void> combined = CompletableFuture.allOf(fetchStudent,fetchUniversity);
        combined.join();
        StudentAndUniversity studentAndUniversity;
        try {
            Student studentFound = fetchStudent.get();
            List<University> universitiesFound = fetchUniversity.get();
            studentAndUniversity = new StudentAndUniversity(studentFound,universitiesFound);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return studentAndUniversity;
    }
}
