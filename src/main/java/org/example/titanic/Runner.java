package org.example.titanic;

import java.io.IOException;
import java.util.List;
import com.opencsv.exceptions.CsvValidationException;
import org.example.titanic.model.Passenger;
import org.example.titanic.service.PassengerService;
import org.example.titanic.service.impl.PassengerServiceWithStream;
import org.example.titanic.service.impl.PassengerServiceWithoutStream;
import org.example.titanic.utils.Utils;

public class Runner {

    public static void main(String[] args) {


        PassengerService<Passenger> calculateServiceWithStream = new PassengerServiceWithStream();
        PassengerService<Passenger> calculateServiceWithoutStream = new PassengerServiceWithoutStream();

        List<Passenger> lists = Utils.getListWithOpenCSV();
        List<Passenger> lists1 = Utils.getListByOwnParser();




        System.out.println("1. Подсчитать средний возраст выживших женщин:");
        System.out.println(calculateServiceWithStream.getAverageAgeFromWomen(lists));

        System.out.println("2. Подсчитать средний возраст утонувших мужчин:");
        System.out.println(calculateServiceWithStream.getAverageAgeFromDrownedMen(lists));

        System.out.println(
            "3. Получить HashMap, где ключ - количество символов в имени, а значение - количество таких имен:");
        System.out.println(calculateServiceWithStream.getMapByNamesLength(lists));


        System.out.println("Без стримов: ");
        System.out.println("1. Подсчитать средний возраст выживших женщин:");
        System.out.println(calculateServiceWithoutStream.getAverageAgeFromWomen(lists1));

        System.out.println("2. Подсчитать средний возраст утонувших мужчин:");
        System.out.println(calculateServiceWithoutStream.getAverageAgeFromDrownedMen(lists1));

        System.out.println(
            "3. Получить HashMap, где ключ - количество символов в имени, а значение - количество таких имен:");
        System.out.println(calculateServiceWithoutStream.getMapByNamesLength(lists1));


    }

}
