package org.example.titanic;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.opencsv.bean.CsvToBeanBuilder;

public class Utils {

    private static List<Titanic> listPassengers;
    public static List<Titanic> getListTitanic() {

        listPassengers = new CsvToBeanBuilder(new InputStreamReader(getClassPathResourceInputStream("Евгений Едвабинский - train.csv")))
            .withType(Titanic.class)
            .build()
            .parse();
        return listPassengers;
    }

    static double roundNumber(double d) {
        return new BigDecimal(d).setScale(2, RoundingMode.DOWN).doubleValue();
    }

    private static InputStream getClassPathResourceInputStream(String fileName) {
        return Utils.class.getClassLoader().getResourceAsStream(fileName);
    }

}
