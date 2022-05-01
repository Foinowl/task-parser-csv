package org.example.titanic.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.titanic.mapper.ConverterPojo;
import org.example.titanic.mapper.GeneralConverter;
import org.example.titanic.mapper.PassengerConverter;
import org.example.titanic.model.Gender;
import org.example.titanic.model.Passenger;
import org.example.titanic.model.RequestBean;
import org.example.titanic.model.Titanic;
import org.example.titanic.parser.CsvReader;

public class Utils {

    private static List<Titanic> listPassengers;
    public static List<? extends RequestBean> getListTitanic() {

        listPassengers = new CsvToBeanBuilder(new InputStreamReader(getClassPathResourceInputStream("train.csv")))
            .withType(Titanic.class)
            .build()
            .parse();
        return listPassengers;
    }

    public static <T> List<? extends RequestBean> getListByType(Supplier<T> type) {

        CsvReader csvReader = new CsvReader.Builder()
            .setInputStream(getClassPathResourceInputStream("train.csv"))
            .setHeading(true)
            .build();


        try {
            csvReader.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<? extends RequestBean> obj = new ArrayList<>();

        Iterator<CsvReader.CsvDetails> iter = csvReader.iterator();
        ConverterPojo<T> passengerConverter = new GeneralConverter<>(type);
        while (iter.hasNext()) {
            obj.add(passengerConverter.convertToRead(iter.next()));
        }
        return obj;
    }


    public static double roundNumber(double d) {
        return new BigDecimal(d).setScale(2, RoundingMode.DOWN).doubleValue();
    }

    static InputStream getClassPathResourceInputStream(String fileName) {
        return Utils.class.getClassLoader().getResourceAsStream(fileName);
    }

    public static Double parseStringToDouble(String value) {
        return value == null || value.isEmpty() ? Double.NaN : Double.parseDouble(value);
    }

    public static double returnPrimitiveFromDouble(Double d) {
        if (d.isNaN() ){
            return 0;
        }
        return d;
    }

    public static boolean isMan(RequestBean requestBean) {
        return requestBean.getGender().isGenderEquals(Gender.MALE);
    }

    public static boolean isWoman(RequestBean requestBean) {
        return requestBean.getGender().isGenderEquals(Gender.FEMALE);
    }

    public static boolean isSurvived(RequestBean requestBean) {
        return requestBean.getSurvived().isSurvived();
    }
}
