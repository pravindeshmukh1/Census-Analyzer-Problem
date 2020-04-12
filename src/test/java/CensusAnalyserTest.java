import com.analyser.dto.IndiaStateCensusCsv;
import com.analyser.dto.IndiaStateCodeCsv;
import com.analyser.dto.USStateCensusCsv;
import com.analyser.services.CensusAnalyser;
import com.analyser.exception.CensusAnalyserException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;


public class CensusAnalyserTest {
    private static String US_CENSUS_FILE_PATH = "src/test/resources/USCensusData.csv";
    private static String CSV_CENSUS_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static String CSV_STATE_CODE_FILE_PATH = "src/test/resources/StateCode.csv";

    //1.1
    @Test
    public void givenIndiaStateCensusCSVFile_whenNumberOfRecordMatchesTrue_shouldReturnNumberOfRecordMatches() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        int noOfCount = censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_CENSUS_FILE_PATH);
        Assert.assertEquals(29, noOfCount);
    }

    //1.2
    @Test
    public void givenIndiaStateCensusCSVFile_whenInCorrect_shouldReturnCustomException() {
        try {
            String WRONG_CSV_FILE_PATH = "src/test/resources/stateCensusData.csv";
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //1.3
    @Test
    public void givenIndiaStateCensusCSVFile_whenFileTypeInCorrect_shouldReturnCustomException() {
        try {
            String WRONG_CSV_FILE_TYPE = "src/test/resources/StateCensusData.txt";
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //1.4
    @Test
    public void givenIndiaStateCensusCSVFile_whenDelimiter_Incorrect_shouldReturnCustomException() {
        try {
            String CSV_FILE_PATH_DELIMITER = "src/test/resources/StateCensusDataDelimiter.csv";
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //1.5
    @Test
    public void givenIndiaStateCensusCSVFile_whenFile_Header_Incorrect_shouldReturnCustomException() {
        try {
            String CSV_FILE_PATH_DELIMITER = "src/test/resources/StateCensusDataDelimiter.csv";
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //2.1
    @Test
    public void givenIndiaStateCodeCSVFile_whenNumberOfRecordMatchesTrue_shouldReturnNumberOfRecordMatches() {
        CSV_STATE_CODE_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            int noOfCount = censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_CENSUS_FILE_PATH, CSV_STATE_CODE_FILE_PATH);
            Assert.assertEquals(37, noOfCount);
        }catch (CensusAnalyserException e){
            e.printStackTrace();
        }
    }

    //2.2
    @Test
    public void givenIndiaStateCodeCSVFile_whenInCorrect_shouldReturnCustomException() {
        try {
            String WRONG_CSV_FILE_PATH = "src/test/resources/stateCode.csv";
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //2.3
    @Test
    public void givenIndiaStateCodeCSVFile_whenFileTypeInCorrect_shouldReturnCustomException() {
        try {
            String WRONG_CSV_FILE_TYPE = "src/test/resources/StateCode.txt";
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //2.4
    @Test
    public void givenIndiaStateCodeCSVFile_whenDelimiter_Incorrect_shouldReturnCustomException() {
        try {
            String CSV_FILE_PATH_DELIMITER = "src/test/resources/StateCodeDelimiter.csv";
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //2.5
    @Test
    public void givenIndiaStateCodeCSVFile_whenFile_Header_Incorrect_shouldReturnCustomException() {
        try {
            String CSV_FILE_PATH_DELIMITER = "src/test/resources/StateCodeDelimiter.csv";
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }
    @Test
    public void givenIndiaCensusData_whenSorted_shouldReturnSortedDataStartState() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.STATE);
            IndiaStateCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, IndiaStateCensusCsv[].class);
            Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_whenSorted_shouldReturnSortedDataEndState() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.STATE);
            IndiaStateCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, IndiaStateCensusCsv[].class);
            Assert.assertEquals("West Bengal", censusCsv[28].state);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_whenSortedState_shouldReturnSortedStartPopulationState() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.POPULATION);
            IndiaStateCensusCsv[] censusCsv = new Gson().fromJson(String.valueOf(sortedCensusData), IndiaStateCensusCsv[].class);
            Assert.assertEquals("Uttar Pradesh", censusCsv[0].state);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }

    }

    @Test
    public void givenIndiaCensusData_whenSortedOnDensity_shouldReturnPopulationDensityState() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.DENSITY);
            IndiaStateCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, IndiaStateCensusCsv[].class);
            Assert.assertEquals("Bihar", censusCsv[0].state);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_whenSortedOnArea_shouldReturnResult() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.AREA);
            IndiaStateCensusCsv[] censusCsv = new Gson().fromJson(sortedCensusData, IndiaStateCensusCsv[].class);
            Assert.assertEquals("Rajasthan", censusCsv[0].state);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    //8
    @Test
    public void givenUSCensusData_whenNumberOfRecordMatches_shouldReturnRecordMatches() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            int noOfCount = censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.US, US_CENSUS_FILE_PATH);
            Assert.assertEquals(51, noOfCount);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    //9
    @Test
    public void givenUsStateData_whenSorted_shouldReturnSortedDataStartState() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.US, US_CENSUS_FILE_PATH);
            String sortedCodeData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.STATE);
            USStateCensusCsv[] codeCsv = new Gson().fromJson(sortedCodeData, USStateCensusCsv[].class);
            Assert.assertEquals("Alabama", codeCsv[0].state);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    @Test
    public void givenUsStateData_whenSorted_shouldReturnSortedDataEndState() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.US, US_CENSUS_FILE_PATH);
            String sortedCodeData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.STATE);
            USStateCensusCsv[] codeCsv = new Gson().fromJson(sortedCodeData, USStateCensusCsv[].class);
            Assert.assertEquals("Wyoming", codeCsv[50].state);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    @Test
    public void givenUsStateData_whenPopulationStateSorted_shouldReturnPopulationStateAndPopulationDensityState() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.US, US_CENSUS_FILE_PATH);
            String sortedCodeData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.POPULATION);
            USStateCensusCsv[] codeCsv = new Gson().fromJson(sortedCodeData, USStateCensusCsv[].class);
            Assert.assertEquals("California", codeCsv[0].state);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    @Test
    public void givenUsCensusData_whenSortedOnDensity_shouldReturnPopulationDensityState() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.US, US_CENSUS_FILE_PATH);
            String sortedCodeData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.POPULATION);
            USStateCensusCsv[] codeCsv = new Gson().fromJson(sortedCodeData, USStateCensusCsv[].class);
            Assert.assertEquals("California", codeCsv[0].state);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    @Test
    public void givenUsCensusData_whenSortedOnArea_shouldReturnResult() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.US, US_CENSUS_FILE_PATH);
            String sortedCodeData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.AREA);
            USStateCensusCsv[] codeCsv = new Gson().fromJson(sortedCodeData, USStateCensusCsv[].class);
            Assert.assertEquals("Alaska", codeCsv[0].state);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    @Test
    public void givenUsStateData_whenSorted_shouldReturnSortedDataStartState1() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            int p = censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.US, US_CENSUS_FILE_PATH);
            String sortedCodeData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.STATE);
            USStateCensusCsv[] codeCsv = new Gson().fromJson(sortedCodeData, USStateCensusCsv[].class);
            Assert.assertEquals("Alabama", codeCsv[0].state);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnDualSort_PopulationAndDensity_ShouldReturnSortedList() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.US, US_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getDualSortByPopulationDensity();
            USStateCensusCsv[] censusCSV = new Gson().fromJson(sortedCensusData, USStateCensusCsv[].class);
            Assert.assertEquals("California", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    //4
    @Test
    public void givenIndiaStateData_whenSorted_shouldReturnSortedDataStartState() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA, CensusAnalyser.SortingMode.STATECODE);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_CENSUS_FILE_PATH,CSV_STATE_CODE_FILE_PATH);
            String sortedCodeData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.STATECODE);
            IndiaStateCodeCsv[] codeCsv = new Gson().fromJson(sortedCodeData, IndiaStateCodeCsv[].class);
            Assert.assertEquals("AD", codeCsv[0].stateCode);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateData_whenSorted_shouldReturnSortedDataEndState() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA, CensusAnalyser.SortingMode.STATECODE);
            censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_CENSUS_FILE_PATH, CSV_STATE_CODE_FILE_PATH);
            String sortedCodeData = censusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.STATECODE);
            IndiaStateCodeCsv[] codeCsv = new Gson().fromJson(sortedCodeData, IndiaStateCodeCsv[].class);
            Assert.assertEquals("WB", codeCsv[36].stateCode);
        } catch (CensusAnalyserException e) {
        throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCsvFile_whenStateCode_shouldReturnCorrectRecord() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            int numberOfRecord = censusAnalyser.loadCensusCsvData(CensusAnalyser.Country.INDIA, CSV_CENSUS_FILE_PATH, CSV_STATE_CODE_FILE_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.getMessage());
        }
    }
}