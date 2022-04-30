package org.example.titanic.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.titanic.model.RequestBean;
import org.example.titanic.model.Titanic;

public class Utils {

    private static List<Titanic> listPassengers;
    public static List<? extends RequestBean> getListTitanic() {

        listPassengers = new CsvToBeanBuilder(new InputStreamReader(getClassPathResourceInputStream("train.csv")))
            .withType(Titanic.class)
            .build()
            .parse();
        return listPassengers;
    }

    static double roundNumber(double d) {
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

}
