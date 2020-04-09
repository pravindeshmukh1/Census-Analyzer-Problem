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
        System.out.println("=======" + censusDaoMap.size());
        return censusDaoMap.size();
    }

    public String getIndiaStateWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (censusDaoList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data Found");
        Comparator<CensusDao> censusCsvComparator = Comparator.comparing(censusDAO -> censusDAO.state);
        this.sorting(censusCsvComparator);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    public String getIndiaStateCodeWiseSortedData(String csvStateCodeFilePath) throws CensusAnalyserException {
        if (censusDaoList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No State Code Data Found");
        Comparator<CensusDao> censusDAOComparator = Comparator.comparing(CensusDAO -> CensusDAO.state);
        this.sorting(censusDAOComparator);
        String sortedStateCodeJson = new Gson().toJson(censusDaoList);
        return sortedStateCodeJson;
    }

    public String getIndiaPopulationWiseSortedCensusData(String censusFilePath) throws CensusAnalyserException {
        if (censusDaoList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDao> indiaCensusDaoComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        this.sorting(indiaCensusDaoComparator);
        Collections.reverse(censusDaoList);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    public String getIndiaDensityWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (censusDaoList.size() == 0 || censusDaoList == null)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDao> indiaCensusDaoComparator = Comparator.comparing(censusDAO -> censusDAO.densityPerSqKm);
        this.sorting(indiaCensusDaoComparator);
        Collections.reverse(censusDaoList);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    public String getIndiaAreaWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (censusDaoList.size() == 0 || censusDaoList == null)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDao> indiaCensusDaoComparator = Comparator.comparing(censusDAO -> censusDAO.areaInSqKm);
        this.sorting(indiaCensusDaoComparator);
        Collections.reverse(censusDaoList);
        String sortedCensusJson = new Gson().toJson(censusDaoList);
        return sortedCensusJson;
    }

    public String getUsStateWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (censusDaoList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Data Found");
        Comparator<CensusDao> censusCsvComparator = Comparator.comparing(censusDAO -> censusDAO.state);
        this.sorting(censusCsvComparator);
        String SortedStateJson = new Gson().toJson(censusDaoList);
        return SortedStateJson;
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