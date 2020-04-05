import com.analyser.dto.StateCensusCsv;
import com.analyser.dto.StateCodeCsv;
import com.analyser.services.CensusAnalyser;
import com.exception.CensusAnalyserException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CensusAnalyserTest {
    private static String CSV_CENSUS_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static String CSV_STATE_CODE_FILE_PATH = "src/test/resources/StateCode.csv";
    private CensusAnalyser censusAnalyser;

    @Before
    public void init() {
        censusAnalyser = new CensusAnalyser();
    }

    //1.1
    @Test
    public void givenStateCensusCSVFile_whenNumberOfRecordMatchesTrue_shouldReturnNumberOfRecordMatches() {
        try {
            int noOfCount = censusAnalyser.loadCsvData(CSV_CENSUS_FILE_PATH);
            Assert.assertEquals(29, noOfCount);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //1.2
    @Test
    public void givenStateCensusCSVFile_whenInCorrect_shouldReturnCustomException() {
        try {
            String WRONG_CSV_FILE_PATH = "src/test/resources/stateCensusData.csv";
            censusAnalyser.loadCsvData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //1.3
    @Test
    public void givenStateCensusCSVFile_whenFileTypeInCorrect_shouldReturnCustomException() {
        try {
            String WRONG_CSV_FILE_TYPE = "src/test/resources/StateCensusData.txt";
            censusAnalyser.loadCsvData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //1.4
    @Test
    public void givenStateCensusCSVFile_whenDelimiter_Incorrect_shouldReturnCustomException() {
        try {
            String CSV_FILE_PATH_DELIMITER = "src/test/resources/StateCensusDataDelimiter.csv";
            censusAnalyser.loadCsvData(CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //1.5
    @Test
    public void givenStateCensusCSVFile_whenFile_Header_Incorrect_shouldReturnCustomException() {
        try {
            String CSV_FILE_PATH_DELIMITER = "src/test/resources/StateCensusDataDelimiter.csv";
            censusAnalyser.loadCsvData(CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //2.1
    @Test
    public void givenStateCodeCSVFile_whenNumberOfRecordMatchesTrue_shouldReturnNumberOfRecordMatches() {
        CSV_STATE_CODE_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            int noOfCount = censusAnalyser.loadStateCodeCsv(CSV_STATE_CODE_FILE_PATH);
            Assert.assertEquals(37, noOfCount);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //2.2
    @Test
    public void givenStateCodeCSVFile_whenInCorrect_shouldReturnCustomException() {
        try {
            String WRONG_CSV_FILE_PATH = "src/test/resources/stateCode.csv";
            censusAnalyser.loadStateCodeCsv(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //2.3
    @Test
    public void givenStateCodeCSVFile_whenFileTypeInCorrect_shouldReturnCustomException() {
        try {
            String WRONG_CSV_FILE_TYPE = "src/test/resources/StateCode.txt";
            censusAnalyser.loadStateCodeCsv(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //2.4
    @Test
    public void givenStateCodeCSVFile_whenDelimiter_Incorrect_shouldReturnCustomException() {
        try {
            String CSV_FILE_PATH_DELIMITER = "src/test/resources/StateCodeDelimiter.csv";
            censusAnalyser.loadStateCodeCsv(CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //2.5
    @Test
    public void givenStateCodeCSVFile_whenFile_Header_Incorrect_shouldReturnCustomException() {
        try {
            String CSV_FILE_PATH_DELIMITER = "src/test/resources/StateCodeDelimiter.csv";
            censusAnalyser.loadStateCodeCsv(CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    @Test
    public void givenIndiaCensusData_whenSorted_shouldReturnSortedDataStartState() {
        try {
            censusAnalyser.loadCsvData(CSV_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(CSV_CENSUS_FILE_PATH);
            StateCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, StateCensusCsv[].class);
            Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_whenSorted_shouldReturnSortedDataEndState() {
        try {
            censusAnalyser.loadCsvData(CSV_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(CSV_CENSUS_FILE_PATH);
            StateCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, StateCensusCsv[].class);
            Assert.assertEquals("West Bengal", censusCsv[28].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //4
    @Test
    public void givenIndiaStateData_whenSorted_shouldReturnSortedDataStartState() {
        try {
            censusAnalyser.loadStateCodeCsv(CSV_STATE_CODE_FILE_PATH);
            String sortedCodeData = censusAnalyser.getStateCodeWiseSortedData();
            StateCodeCsv[] codeCsv = new Gson().fromJson(sortedCodeData, StateCodeCsv[].class);
            Assert.assertEquals("AD", codeCsv[0].stateCode);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaStateData_whenSorted_shouldReturnSortedDataEndState() {
        try {
            censusAnalyser.loadStateCodeCsv(CSV_STATE_CODE_FILE_PATH);
            String sortedCodeData = censusAnalyser.getStateCodeWiseSortedData();
            StateCodeCsv[] codeCsv = new Gson().fromJson(sortedCodeData, StateCodeCsv[].class);
            Assert.assertEquals("WB", codeCsv[36].stateCode);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeCsvFile_returnCorrectRecord() {
        try {
            censusAnalyser.loadCsvData(CSV_CENSUS_FILE_PATH);
            int numberOfRecord = censusAnalyser.loadStateCodeCsv(CSV_STATE_CODE_FILE_PATH);
            Assert.assertEquals(66, numberOfRecord);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_whenSortedState_shouldReturnSortedStartPopulationState() {
        try {
            censusAnalyser.loadCsvData(CSV_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData(CSV_CENSUS_FILE_PATH);
            StateCensusCsv[] censusCsv = new Gson().fromJson(String.valueOf(sortedCensusData), StateCensusCsv[].class);
            Assert.assertEquals(607688, censusCsv[0].population);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_whenSortedOnDensity_shouldReturnPopulationDensityState() {
        try {
            censusAnalyser.loadCsvData(CSV_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getDensityWiseSortedCensusData(CSV_CENSUS_FILE_PATH);
            StateCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, StateCensusCsv[].class);
            Assert.assertEquals(1102, censusCsv[0].densityPerSqKm);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
