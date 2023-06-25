package com.example.search.controller;

import com.example.search.pojo.StudentAndUniversity;
import com.example.search.service.StudentAndUniversityService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

    private final StudentAndUniversityService studentAndUniversityService;

    @Autowired
    public SearchController(StudentAndUniversityService studentAndUniversityService){
        this.studentAndUniversityService = studentAndUniversityService;
    }

    @GetMapping("/weather/search")
    @ApiOperation(value="Default test method")
    public ResponseEntity<?> getDetails() {
        return new ResponseEntity<>("this is search service", HttpStatus.OK);
    }

    @GetMapping("/weather/fallback")
    @HystrixCommand(fallbackMethod = "fallback")
    @ApiOperation(value="Simulate a fallback to test Hystrix")
    @ApiResponses({
            @ApiResponse(code = 500, message = "it should return 500 in fallback function")
    })
    public ResponseEntity<?> simulateFallback() {

        throw new RuntimeException("fallback here");
    }


    @RequestMapping(value="/weather/detail", params = {"id", "country"}, method = RequestMethod.GET)
    @ApiOperation(value="Get details of student with id and unversities in some country")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully return results"),
            @ApiResponse(code = 404, message = "Cannot find any student or university matched"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<?> getStudentByIdAndUniversityByCountry(
            @ApiParam(value = "Student id", example = "1") @RequestParam String id,
            @ApiParam(value = "Country name", example = "China") @RequestParam String country) {
        StudentAndUniversity studentAndUniversity = studentAndUniversityService.getStudentByIdAndUniversityByCountry(id,country);
        if((studentAndUniversity.getUniversities()==null || studentAndUniversity.getUniversities().isEmpty()) && studentAndUniversity.getStudent()==null){
            return new ResponseEntity<>(studentAndUniversity, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAndUniversity, HttpStatus.OK);
    }

    @RequestMapping(value="/weather/detail", params = {"id", "name"},  method = RequestMethod.GET)
    @ApiOperation(value="Get details of student with id and unversities contains some name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully return results"),
            @ApiResponse(code = 404, message = "Cannot find any student or university matched"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<?> getStudentByIdAndUniversityByName(
            @ApiParam(value = "Student id", example = "1") @RequestParam String id,
            @ApiParam(value = "Words in university name", example = "Beijing") @RequestParam String name) {
        StudentAndUniversity studentAndUniversity = studentAndUniversityService.getStudentByIdAndUniversityByName(id,name);
        if((studentAndUniversity.getUniversities()==null || studentAndUniversity.getUniversities().isEmpty()) && studentAndUniversity.getStudent()==null){
            return new ResponseEntity<>(studentAndUniversity, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAndUniversity, HttpStatus.OK);
    }

    private ResponseEntity<?> fallback(){
        return new ResponseEntity<>("There is a fallback!",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
