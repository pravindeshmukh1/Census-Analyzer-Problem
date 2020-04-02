import com.censusanalyser.CensusAnalyser;
import com.exception.CensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest {
    private static String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static String WRONG_CSV_FILE_PATH = "src/test/resources/stateCensusData.csv";
    private static String WRONG_CSV_FILE_TYPE = "src/test/resources/StateCensusData.txt";
    private static String CSV_FILE_PATH_DELIMITER = "src/test/resources/StateCensusDataDelimiter.csv";

    @Test
    public void givenStateCensusCSVFile_whenNumberOfRecordMatchesTrue_shouldReturnNumberOfRecordMatches() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int noOfCount = censusAnalyser.loadCsvData(CSV_FILE_PATH);
        Assert.assertEquals(29, noOfCount);
    }

    @Test
    public void givenStateCensusCSVFile_whenInCorrect_shouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCsvData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenFileTypeInCorrect_shouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCsvData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenDelimiter_Incorrect_shouldReturnCustomException() {
        CensusAnalyser censusAnalyse = new CensusAnalyser();
        try {
            censusAnalyse.loadCsvData(CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_INCORRECT_EXCEPTION, e.exceptionType);
        }
    }
}
