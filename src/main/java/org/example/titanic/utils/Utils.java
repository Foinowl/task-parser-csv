package org.example.titanic.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.titanic.mapper.ConverterPojo;
import org.example.titanic.mapper.PassengerConverter;
import org.example.titanic.model.Gender;
import org.example.titanic.model.Passenger;
import org.example.titanic.parser.CsvReader;

public class Utils {

    public static List<Passenger> getListWithOpenCSV() {

        return new CsvToBeanBuilder(new InputStreamReader(getClassPathResourceInputStream("train.csv")))
            .withType(Passenger.class)
            .build()
            .parse();
    }

    public static List<Passenger> getListByOwnParser() {

        CsvReader csvReader = new CsvReader.Builder()
            .setInputStream(getClassPathResourceInputStream("train.csv"))
            .setHeading(true)
            .setSeparator(Constants.COMMA)
            .build();


        try {
            csvReader.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Passenger> obj = new ArrayList<>();

        Iterator<CsvReader.CsvDetails> iter = csvReader.iterator();
        ConverterPojo<Passenger> passengerConverter = new PassengerConverter();
        while (iter.hasNext()) {
            obj.add(passengerConverter.convertToObject(iter.next()));
        }
        return obj;
    }


    public static double roundNumber(Double d) {
        return new BigDecimal(d).setScale(2, RoundingMode.DOWN).doubleValue();
    }

    static InputStream getClassPathResourceInputStream(String fileName) {
        return Utils.class.getClassLoader().getResourceAsStream(fileName);
    }

    public static Double parseStringToDouble(String value) {
        return value == null || value.isEmpty() ? Double.NaN : Double.parseDouble(value);
    }

    public static Double returnDoubleWithoutNaN(Double d) {
        if (d.isNaN()) {
            return 0.0;
        }
        return d;
    }

    public static boolean isMan(Passenger passenger) {
        return passenger.getGender().isGenderEquals(Gender.MALE);
    }

    public static boolean isWoman(Passenger passenger) {
        return passenger.getGender().isGenderEquals(Gender.FEMALE);
    }

    public static boolean isSurvived(Passenger passenger) {
        return passenger.getSurvived().isSurvived();
    }
}
