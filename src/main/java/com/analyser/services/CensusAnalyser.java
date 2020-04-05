package com.analyser.services;

import com.analyser.dao.CensusDAO;
import com.analyser.dto.StateCensusCsv;
import com.analyser.dto.StateCodeCsv;
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

public class CensusAnalyser {
    private static final String CSV_CENSUS_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String CSV_STATE_CODE_FILE_PATH = "src/test/resources/StateCode.csv";

    public static void main(String[] args) {
        System.out.println("Welcome Census Analyser");
    }

    List<CensusDAO> censusDAOList;
    Map<String, CensusDAO> censusDAOMap;

    public CensusAnalyser() {
        this.censusDAOList = new ArrayList<>();
        this.censusDAOMap = new HashMap<>();
    }

    public int loadCsvData(String csvFilePath) throws CensusAnalyserException {
        int noOfRecord = 0;
        String fileExtension = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!fileExtension.equals("csv")) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, fileExtension.intern());
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<StateCensusCsv> csvFileIterator = icsvBuilder.getCSVFileIterator(reader, StateCensusCsv.class);

            while (csvFileIterator.hasNext()) {
                StateCensusCsv censusCsv = csvFileIterator.next();
                CensusDAO censusDAO = censusDAOMap.put(censusCsv.state, new CensusDAO(censusCsv));
                noOfRecord++;
            }
            censusDAOList = censusDAOMap.values().stream().collect(Collectors.toList());
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return noOfRecord;
    }

    public int loadStateCodeCsv(String csvStateCodeFilePath) throws CensusAnalyserException {
        int noOfRecord = 0;
        String fileExtension = csvStateCodeFilePath.substring(csvStateCodeFilePath.lastIndexOf(".") + 1);
        if (!fileExtension.equals("csv")) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, fileExtension);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvStateCodeFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<StateCodeCsv> csvFileIterator = icsvBuilder.getCSVFileIterator(reader, StateCodeCsv.class);
            while (csvFileIterator.hasNext()) {
                StateCodeCsv codeCsv = csvFileIterator.next();
                CensusDAO censusDAO = censusDAOMap.put(codeCsv.stateCode, new CensusDAO(codeCsv.stateCode));
                censusDAOList = censusDAOMap.values().stream().collect(Collectors.toList());
            }
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
        Comparator<CensusDAO> censusCsvComparator = Comparator.comparing(censusCsv -> censusCsv.state);
        this.sorting(censusCsvComparator);
        String sortedCensusJson = new Gson().toJson(censusDAOList);
        return sortedCensusJson;
    }

    public String getStateCodeWiseSortedData() throws CensusAnalyserException {
        if (censusDAOList.size() == 0 | censusDAOList == null)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No State Code Data Found");
        Comparator<CensusDAO> censusDAOComparator = Comparator.comparing(censusDAO -> censusDAO.stateCode);
        this.sorting(censusDAOComparator);
        String sortedStateCodeJson = new Gson().toJson(censusDAOList);
        return sortedStateCodeJson;
    }

    public String getPopulationWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (censusDAOList.size() == 0 || censusDAOList == null)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDAO> indiaCensusDaoComparator = Comparator.comparing(census -> census.population);
        this.sorting(indiaCensusDaoComparator);
        String sortedCensusJson = new Gson().toJson(censusDAOList);
        return sortedCensusJson;
    }

    public String getDensityWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (censusDAOList.size() == 0 || censusDAOList == null)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDAO> indiaCensusDaoComparator = Comparator.comparing(census -> census.densityPerSqKm);
        this.sorting(indiaCensusDaoComparator);
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
}