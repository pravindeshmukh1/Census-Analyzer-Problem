import com.censusanalyser.CensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest {
    private static final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";

    @Test
    public void givenStateCensusCSVFile_whenNumberOfRecordMatchesTrue_shouldReturnNumberOfRecordMatches() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int noOfCount = censusAnalyser.loadCsvData(CSV_FILE_PATH);
        Assert.assertEquals(29, noOfCount);
    }
}
