package com.example.Countries.service.serviceImpl;

import com.example.Countries.models.CountryFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class CountriesServiceImpl implements CountriesService {
    final String url = "https://restcountries.com/v3.1/";

    @Override
    public List<LinkedHashMap<String, ?>> getAllCountries() {
        RestTemplate restTemplate = new RestTemplate();
        //Get all countries list
        List<LinkedHashMap<String, ?>> countries = new ArrayList<>();
        try {
            countries = restTemplate.getForObject(url + "all", ArrayList.class);
        } catch (RestClientException exception) {
            exception.printStackTrace();
        }
        return countries;
    }

    @Override
    public List<LinkedHashMap<String, ?>> getCountriesByName(String name) {
        RestTemplate restTemplate = new RestTemplate();
        //Get countries list for given name of country
        List<LinkedHashMap<String, ?>> countries = new ArrayList<>();
        try {
            countries = restTemplate.getForObject(url + "name/" + name, ArrayList.class);
        } catch (RestClientException exception) {
            exception.printStackTrace();
        }
        return countries;
    }

    @Override
    public List<LinkedHashMap<String, ?>> getFilteredCountries(CountryFilter countryFilter, String sort, Integer page, Integer size) {
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, ?>> countries = new ArrayList<>();
        try {
            countries = restTemplate.getForObject(url + "all", ArrayList.class);
        } catch (RestClientException exception) {
            exception.printStackTrace();
        }
        //  Filter countries list for given population range
        if (countryFilter.getPopulationMin() != null && countryFilter.getPopulationMax() != null) {
            countries = countries.stream()
                    .filter(country -> (Integer) country.get("population") > countryFilter.getPopulationMin() && (Integer) country.get("population") < countryFilter.getPopulationMax())
                    .collect(Collectors.toList());
        }
        //  Filter countries list for given area range
        if (countryFilter.getAreaMax() != null && countryFilter.getAreaMin() != null) {
            countries = countries.stream()
                    .filter(country -> (Double) country.get("area") > countryFilter.getAreaMin() && (Double) country.get("area") < countryFilter.getAreaMax())
                    .collect(Collectors.toList());
        }
        //  Filter countries list for given language
        if (countryFilter.getLanguage() != null) {
            countries = countries.stream()
                    .filter(country -> country.get("languages") != null && ((HashMap) country.get("languages")).values().contains(countryFilter.getLanguage()))
                    .collect(Collectors.toList());
        }
        //  Return countries list in alphabetical order of names: Ascending(default) , Descending( sort = desc)
        Comparator<LinkedHashMap<String, ?>> comparator = (country1, country2) -> {
            String name1 = (String) ((HashMap) country1.get("name")).get("common");
            String name2 = (String) ((HashMap) country2.get("name")).get("common");
            if (name1 != null && name2 != null && sort.equals("desc")) {
                return name2.compareTo(name1);
            } else {
                return name1.compareTo(name2);
            }
        };
        countries = countries.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        // Apply pagination for countries list
        int start = (page - 1) * size;
        int end = Math.min(start + size, countries.size());
        if (start > countries.size()) {
            return List.of(); // Return empty list if start index is out of range
        }
        return countries.subList(start, end);
    }

}
