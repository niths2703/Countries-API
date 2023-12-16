package com.example.Countries.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CountryFilter {
    Double areaMin;
    Double areaMax;
    Integer populationMin;
    Integer populationMax;
    String language;
}
