package org.example.titanic;

import java.io.IOException;
import java.util.List;
import com.opencsv.exceptions.CsvValidationException;
import org.example.titanic.model.RequestBean;
import org.example.titanic.utils.Utils;

public class Runner {

    public static void main(String[] args) throws IOException, CsvValidationException {


//        CsvReader csvReader = new CsvReader.Builder()
//            .setInputStream(getClassPathResourceInputStream("train.csv"))
//            .setHeading(true)
//            .build();
//
//        csvReader.parse();
//        CsvReader.CsvDetails csvDetails = null;
//        List<Object> obj = new ArrayList<>();
//
//        Iterator<CsvReader.CsvDetails> iter = csvReader.iterator();
//        while (iter.hasNext()) {
//            csvDetails = iter.next();
//            csvDetails.getByIndex(0);
//            obj.add(csvDetails.getByColumn("PassengerId"));
//            csvDetails.getByColumn("Survived");
//            csvDetails.getByColumn("Sex");
//            csvDetails.getByIndex(1);
//            csvDetails.getByIndex(2);
//        }

        CalculateService calculateService = new LogicServiceWithStream();
        CalculateService calculateService1 = new LogicServiceWithoutStream();

        List<? extends RequestBean> lists = Utils.getListTitanic();
        List<? extends RequestBean> list1 = Utils.getListPassenger();

        System.out.println("1. Подсчитать средний возраст выживших женщин:");
        System.out.println(calculateService.getAverageAgeFromWomen(list1));

        System.out.println("2. Подсчитать средний возраст утонувших мужчин:");
        System.out.println(calculateService.getAverageAgeFromDrownedMen(list1));

        System.out.println(
            "3. Получить HashMap, где ключ - количество символов в имени, а значение - количество таких имен:");
        System.out.println(calculateService.getMapByNamesLength(list1));


        System.out.println("Без стримов: ");
        System.out.println("1. Подсчитать средний возраст выживших женщин:");
        System.out.println(calculateService1.getAverageAgeFromWomen(lists));

        System.out.println("2. Подсчитать средний возраст утонувших мужчин:");
        System.out.println(calculateService1.getAverageAgeFromDrownedMen(lists));

        System.out.println(
            "3. Получить HashMap, где ключ - количество символов в имени, а значение - количество таких имен:");
        System.out.println(calculateService1.getMapByNamesLength(lists));


    }

}
