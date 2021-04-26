package comDesignPrinciples;

import org.junit.Assert;
import org.junit.Test;

public class IndianCensusAnalyserTest {
    private static final String ORIGINAL_FILE_PATH = "C:\\Users\\kirankumar\\IdeaProjects\\DesignPrinciplesDay29\\src\\main\\resources\\india-districts-census-2011.csv";

    @Test
    public void givenCorrect(){
        try{
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(ORIGINAL_FILE_PATH);
            Assert.assertEquals(640,numOfRecords);
        }catch(CensusAnalyserException e){}
    }
}
