package com.example.search.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class University {
    String country;
    @JsonProperty("alpha_two_code")
    String alpha_two_code;
    String name;
    @JsonProperty("state-province")
    String stateProvince;
    List<String> domains;

    @JsonProperty("web_pages")
    List<String> web_pages;
}
