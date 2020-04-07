package com.analyser.services;

import com.analyser.dao.CensusDAO;
import com.analyser.dto.IndiaStateCensusCsv;
import com.analyser.dto.IndiaStateCodeCsv;
import com.analyser.dto.USStateCensusCsv;
import com.analyser.factory.CSVBuilderFactory;
import com.analyser.factory.ICSVBuilder;
import com.exception.CensusAnalyserException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    public static void main(String[] args) {
        System.out.println("Welcome Census Analyser");
    }

    List<CensusDAO> censusDAOList;
    Map<String, CensusDAO> censusDAOMap;

    public CensusAnalyser() {
        this.censusDAOList = new ArrayList<>();
        this.censusDAOMap = new HashMap<>();
    }

    public int loadIndiaCensusCsvData(String csvFilePath) throws CensusAnalyserException {
        int noOfRecord = 0;
        String fileExtension = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!fileExtension.equals("csv")) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, fileExtension.intern());
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCensusCsv> csvFileIterator = icsvBuilder.getCSVFileIterator(reader, IndiaStateCensusCsv.class);
            Iterable<IndiaStateCensusCsv> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(IndiaStateCensusCsv -> censusDAOMap.put(IndiaStateCensusCsv.state, new CensusDAO(IndiaStateCensusCsv)));
            censusDAOList = censusDAOMap.values().stream().collect(Collectors.toList());
            noOfRecord = censusDAOMap.size();
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return noOfRecord;
    }

    public int loadIndiaStateCodeCsv(String csvStateCodeFilePath) throws CensusAnalyserException {
        int noOfRecord = 0;
        String fileExtension = csvStateCodeFilePath.substring(csvStateCodeFilePath.lastIndexOf(".") + 1);
        if (!fileExtension.equals("csv")) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, fileExtension);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvStateCodeFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCsv> csvFileIterator = icsvBuilder.getCSVFileIterator(reader, IndiaStateCodeCsv.class);
            Iterable<IndiaStateCodeCsv> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(IndiaStateCodeCsv -> censusDAOMap.put(IndiaStateCodeCsv.stateName, new CensusDAO(IndiaStateCodeCsv)));
            censusDAOList = censusDAOMap.values().stream().collect(Collectors.toList());
            noOfRecord = censusDAOMap.size();
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return noOfRecord;
    }

    public String getStateWiseSortedCensusData(String csvCensusFilePath) throws CensusAnalyserException {
        if (censusDAOList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data Found");
        Comparator<CensusDAO> censusCsvComparator = Comparator.comparing(censusDAO -> censusDAO.state); //(censusCsv -> censusCsv.state);
        this.sorting(censusCsvComparator);
        String sortedCensusJson = new Gson().toJson(censusDAOList);
        return sortedCensusJson;
    }

    public String getStateCodeWiseSortedData() throws CensusAnalyserException {
        if (censusDAOList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No State Code Data Found");
        Comparator<CensusDAO> censusDAOComparator = Comparator.comparing(censusDAO -> censusDAO.stateCode);
        this.sorting(censusDAOComparator);
        String sortedStateCodeJson = new Gson().toJson(censusDAOList);
        return sortedStateCodeJson;
    }

    public String getPopulationWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (censusDAOList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDAO> indiaCensusDaoComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        this.sorting(indiaCensusDaoComparator);
        Collections.reverse(censusDAOList);
        String sortedCensusJson = new Gson().toJson(censusDAOList);
        return sortedCensusJson;
    }

    public String getDensityWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (censusDAOList.size() == 0 || censusDAOList == null)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDAO> indiaCensusDaoComparator = Comparator.comparing(censusDAO -> censusDAO.densityPerSqKm);
        this.sorting(indiaCensusDaoComparator);
        Collections.reverse(censusDAOList);
        String sortedCensusJson = new Gson().toJson(censusDAOList);
        return sortedCensusJson;
    }

    public String getAreaWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (censusDAOList.size() == 0 || censusDAOList == null)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDAO> indiaCensusDaoComparator = Comparator.comparing(censusDAO -> censusDAO.areaInSqKm);
        this.sorting(indiaCensusDaoComparator);
        Collections.reverse(censusDAOList);
        String sortedCensusJson = new Gson().toJson(censusDAOList);
        return sortedCensusJson;
    }

    private List sorting(Comparator<CensusDAO> daoComparator) {
        for (int i = 0; i < censusDAOList.size() - 1; i++) {
            for (int j = 0; j < censusDAOList.size() - i - 1; j++) {
                CensusDAO csv1 = censusDAOList.get(j);
                CensusDAO csv2 = censusDAOList.get(j + 1);
                if (daoComparator.compare(csv1, csv2) > 0) {
                    censusDAOList.set(j, csv2);
                    censusDAOList.set(j + 1, csv1);
                }
            }
        }
        return censusDAOList;
    }

    public int loadUSCensusCsvData(String usCensusFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(usCensusFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<USStateCensusCsv> csvFileIterator = icsvBuilder.getCSVFileIterator(reader, USStateCensusCsv.class);
            while (csvFileIterator.hasNext()) {
                USStateCensusCsv nextCensusCsv = csvFileIterator.next();
                censusDAOMap.put(nextCensusCsv.state, new CensusDAO(nextCensusCsv));
            }
            censusDAOList = censusDAOMap.values().stream().collect(Collectors.toList());
            return censusDAOMap.size();

        } catch (CensusAnalyserException | IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        }
    }
}