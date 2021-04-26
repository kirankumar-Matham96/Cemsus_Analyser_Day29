package comDesignPrinciples;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class IndianCensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        /**
         * try with resources
         * Reader is used inside try to get the file path
         */
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBeanBuilder<IndiaCensusGettingPOJO> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusGettingPOJO.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusGettingPOJO> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaCensusGettingPOJO> censusCSVIterator = csvToBean.iterator();
            Iterable<IndiaCensusGettingPOJO> csvIterable = () -> censusCSVIterator;
            int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
            return numOfEntries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException
                    .ExceptionType
                    .CENSUS_FILE_PROBLEM);
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException
                    .ExceptionType
                    .UNABLE_TO_PARSE);
        }
    }

    public static void main(String[] args) throws CensusAnalyserException {
        IndianCensusAnalyser indianCensusAnalyser = new IndianCensusAnalyser();
        int number = indianCensusAnalyser.loadIndiaCensusData("C:\\Users\\kirankumar\\IdeaProjects\\DesignPrinciplesDay29\\src\\main\\resources\\india-districts-census-2011.csv");
        System.out.println(number);//Should give 640
    }
}
