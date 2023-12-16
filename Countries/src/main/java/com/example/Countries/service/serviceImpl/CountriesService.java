package com.example.Countries.service.serviceImpl;

import com.example.Countries.models.CountryFilter;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public interface CountriesService {
    List<LinkedHashMap<String, ?>> getAllCountries();

    List<LinkedHashMap<String, ?>> getCountriesByName(String name);

    List<LinkedHashMap<String, ?>> getFilteredCountries(CountryFilter countryFilter, String sort, Integer page, Integer size);
}
