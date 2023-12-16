package com.example.Countries.controllers;

import com.example.Countries.models.CountryFilter;
import com.example.Countries.service.serviceImpl.CountriesService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
public class CountriesController {

    private final CountriesService countriesService;

    @GetMapping("/countries")
    public ResponseEntity<?> getAllCountries() throws IOException {
        log.info("API to get list of all countries.");
        return ResponseEntity.ok(countriesService.getAllCountries());
    }

    @GetMapping("/countries/{name}")
    public ResponseEntity<?> getCountryByName(@PathVariable String name){
        log.info("API to get country by name.");
        return ResponseEntity.ok(countriesService.getCountriesByName(name));
    }

    @PostMapping("/countries/filter/{sort}/{page}/{size}")
    public ResponseEntity<?> getFilteredCountries(@RequestBody CountryFilter countryFilter, @PathVariable String sort, @PathVariable Integer page, @PathVariable Integer size){
        log.info("API to get filtered and sorted list of countries.");
        return ResponseEntity.ok(countriesService.getFilteredCountries(countryFilter,sort,page,size));
    }
}
