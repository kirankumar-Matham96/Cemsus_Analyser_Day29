package comDesignPrinciples;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IndianCensusAnalyserTest {
    private static final String ORIGINAL_FILE_PATH = "C:\\Users\\kirankumar\\IdeaProjects\\DesignPrinciplesDay29\\src\\main\\resources\\india-districts-census-2011.csv";
    private static final String WRONG_FILE_PATH = "C:\\Users\\kirankumar\\IdeaProjects\\DesignPrinciplesDay29\\src\\test\\resources\\india-districts-census-2011.csv";

    @Test
    public void givenCorrectFilePathPassTheTest() {
        try {
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(ORIGINAL_FILE_PATH);
            Assert.assertEquals(640, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenWrongPathReturnsCustomException() {
        try {
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            int numOfRecords = censusAnalyser.loadIndiaCensusData(WRONG_FILE_PATH);
            Assert.assertEquals(640, numOfRecords);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
}
