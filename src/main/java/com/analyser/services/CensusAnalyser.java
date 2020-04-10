package com.analyser.services;

import com.analyser.adapter.CensusAdapterFactory;
import com.analyser.dao.CensusDao;
import com.analyser.exception.CensusAnalyserException;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {


    public enum Country {INDIA, US}

    public static void main(String[] args) {
        System.out.println("Welcome Census Analyser");
    }

    List<CensusDao> censusDaoList;
    Map<String, CensusDao> censusDaoMap;

    public CensusAnalyser(Country country) {
        this.censusDaoList = new ArrayList<>();
        this.censusDaoMap = new HashMap<>();
    }

    public int loadCensusCsvData(Country country, String... csvFilePath) throws CensusAnalyserException {
        censusDaoMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        censusDaoList = censusDaoMap.values().stream().collect(Collectors.toList());
        return censusDaoMap.size();
    }

    public String getStateWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (censusDaoList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data Found");
        Comparator<CensusDao> censusDaoComparator = Comparator.comparing(censusDAO -> censusDAO.state);
        this.sorting(censusDaoComparator);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    public String getIndiaStateCodeWiseSortedData(String csvStateCodeFilePath) throws CensusAnalyserException {
        if (censusDaoList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No State Code Data Found");
        Comparator<CensusDao> censusDaoComparator = Comparator.comparing(CensusDAO -> CensusDAO.stateCode);
        this.sorting(censusDaoComparator);
        String sortedStateCodeJson = new Gson().toJson(censusDaoList);
        return sortedStateCodeJson;
    }

    public String getPopulationWiseSortedCensusData(String censusFilePath) throws CensusAnalyserException {
        if (censusDaoList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDao> censusDaoComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        this.sorting(censusDaoComparator);
        Collections.reverse(censusDaoList);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    public String getDensityWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (censusDaoList.size() == 0 || censusDaoList == null)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDao> censusDaoComparator = Comparator.comparing(censusDAO -> censusDAO.densityPerSqKm);
        this.sorting(censusDaoComparator);
        Collections.reverse(censusDaoList);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    public String getAreaWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (censusDaoList.size() == 0 || censusDaoList == null)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDao> censusDaoComparator = Comparator.comparing(censusDAO -> censusDAO.areaInSqKm);
        this.sorting(censusDaoComparator);
        Collections.reverse(censusDaoList);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    private void sorting(Comparator<CensusDao> daoComparator) {
        for (int i = 0; i < censusDaoList.size() - 1; i++) {
            for (int j = 0; j < censusDaoList.size() - i - 1; j++) {
                CensusDao csv1 = censusDaoList.get(j);
                CensusDao csv2 = censusDaoList.get(j + 1);
                if (daoComparator.compare(csv1, csv2) > 0) {
                    censusDaoList.set(j, csv2);
                    censusDaoList.set(j + 1, csv1);
                }
            }
        }
    }
}