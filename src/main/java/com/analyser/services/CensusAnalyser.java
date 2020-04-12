package com.analyser.services;

import com.analyser.adapter.CensusAdapterFactory;
import com.analyser.dao.CensusDao;
import com.analyser.exception.CensusAnalyserException;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {
    private final Country country;
    public SortingMode mode;
    List<CensusDao> censusDaoList;
    Map<String, CensusDao> censusDaoMap;

    public CensusAnalyser(Country country, SortingMode mode) {
        this.country = country;
        this.mode = mode;
    }

    public enum SortingMode {
        STATE, POPULATION, AREA, DENSITY, STATECODE
    }

    public enum Country {INDIA, US}

    public static void main(String[] args) {
        System.out.println("Welcome Census Analyser");
    }

    public CensusAnalyser(Country country) {
        this.country = country;
    }

    public int loadCensusCsvData(Country country, String... csvFilePath) throws CensusAnalyserException {
        censusDaoMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        censusDaoList = censusDaoMap.values().stream().collect(Collectors.toList());
        return censusDaoMap.size();
    }

    public String getSortedCensusData(SortingMode mode) throws CensusAnalyserException {
        if (censusDaoMap == null || censusDaoMap.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        ArrayList censusDTO = censusDaoMap.values().stream()
                .sorted(Objects.requireNonNull(CensusDao.getSortComparator(mode)))
                .map(censusDAO -> censusDAO.getCensusDTO(country, mode))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusDTO);
    }

    public String getDualSortByPopulationDensity() throws CensusAnalyserException {
        if (censusDaoMap == null || censusDaoMap.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        ArrayList censusDTO = censusDaoMap.values().stream()
                .sorted(Comparator.comparingInt(CensusDao::getPopulation).thenComparingDouble(CensusDao::getPopulationDensity).reversed())
                .map(c -> c.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusDTO);
    }
}