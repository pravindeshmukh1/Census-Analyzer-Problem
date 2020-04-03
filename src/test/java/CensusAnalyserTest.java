import com.analyser.services.CensusAnalyser;
import com.analyser.services.StateCodeAnalyser;
import com.exception.CensusAnalyserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CensusAnalyserTest {
    private static String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static String CSV_STATE_CODE_PATH = "src/test/resources/StateCode.csv";
    CensusAnalyser censusAnalyser;
    StateCodeAnalyser stateCodeAnalyse;

    @Before
    public void init() {
        censusAnalyser = new CensusAnalyser();
        stateCodeAnalyse = new StateCodeAnalyser();
    }

    //1.1
    @Test
    public void givenStateCensusCSVFile_whenNumberOfRecordMatchesTrue_shouldReturnNumberOfRecordMatches() throws CensusAnalyserException {
        int noOfCount = censusAnalyser.loadCsvData(CSV_FILE_PATH);
        Assert.assertEquals(29, noOfCount);
    }

    //1.2
    @Test
    public void givenStateCensusCSVFile_whenInCorrect_shouldReturnCustomException() {
        try {
            String WRONG_CSV_FILE_PATH = "src/test/resources/stateCensusData.csv";
            censusAnalyser.loadCsvData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.exceptionType);
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
    public void givenStateCodeCSVFile_whenNumberOfRecordMatchesTrue_shouldReturnNumberOfRecordMatches() throws CensusAnalyserException {
        CSV_STATE_CODE_PATH = "src/test/resources/StateCode.csv";
        int noOfCount = stateCodeAnalyse.loadStateCodeCsv(CSV_STATE_CODE_PATH);
        Assert.assertEquals(37, noOfCount);
    }

    //2.2
    @Test
    public void givenStateCodeCSVFile_whenInCorrect_shouldReturnCustomException() {
        try {
            String WRONG_CSV_FILE_PATH = "src/test/resources/stateCode.csv";
            stateCodeAnalyse.loadStateCodeCsv(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    //2.3
    @Test
    public void givenStateCodeCSVFile_whenFileTypeInCorrect_shouldReturnCustomException() {
        try {
            String WRONG_CSV_FILE_TYPE = "src/test/resources/StateCode.txt";
            stateCodeAnalyse.loadStateCodeCsv(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }
}
