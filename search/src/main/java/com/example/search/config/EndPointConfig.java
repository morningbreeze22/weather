package com.example.search.config;

public class EndPointConfig {

    public static final String gatewayPort = "8200";


    public static final String queryStudentById = "http://localhost:"+ gatewayPort + "/student/";
    public static final String queryUniversityByCountry = "http://localhost:"+ gatewayPort + "/university?country=";

    public static final String queryUniversityByName = "http://localhost:"+ gatewayPort + "/university?name=";
}
