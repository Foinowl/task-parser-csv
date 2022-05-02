package org.example.titanic;

import java.util.List;
import org.example.titanic.model.Passenger;
import org.example.titanic.service.PassengerService;
import org.example.titanic.service.impl.PassengerServiceWithStream;
import org.example.titanic.service.impl.PassengerServiceWithoutStream;
import org.example.titanic.utils.Utils;

public class Runner {

    public static void main(String[] args) {


        PassengerService<Passenger> calculateServiceWithStream = new PassengerServiceWithStream();
        PassengerService<Passenger> calculateServiceWithoutStream = new PassengerServiceWithoutStream();

//        List<PassengerService<Passenger>> passengerServiceList = List.of(calculateServiceWithStream, calculateServiceWithoutStream);

        List<Passenger> lists = Utils.getListWithOpenCSV();
        List<Passenger> lists1 = Utils.getListByOwnParser();
        List<Passenger> lists2 = Utils.getListByOwnParserWithBean();

        List<List<Passenger>> allLists = List.of(lists, lists1, lists2);


        System.out.println("Стримы:");
        for (List<Passenger> passengers : allLists) {
            System.out.println("1. Подсчитать средний возраст выживших женщин:");
            System.out.println(calculateServiceWithStream.getAverageAgeFromWomen(passengers));

            System.out.println("2. Подсчитать средний возраст утонувших мужчин:");
            System.out.println(calculateServiceWithStream.getAverageAgeFromDrownedMen(passengers));

            System.out.println(
                "3. Получить HashMap, где ключ - количество символов в имени, а значение - количество таких имен:");
            System.out.println(calculateServiceWithStream.getMapByNamesLength(passengers));
        }


        System.out.println("Циклы:");
        for (List<Passenger> passengers : allLists) {
            System.out.println("1. Подсчитать средний возраст выживших женщин:");
            System.out.println(calculateServiceWithoutStream.getAverageAgeFromWomen(passengers));

            System.out.println("2. Подсчитать средний возраст утонувших мужчин:");
            System.out.println(calculateServiceWithoutStream.getAverageAgeFromDrownedMen(passengers));

            System.out.println(
                "3. Получить HashMap, где ключ - количество символов в имени, а значение - количество таких имен:");
            System.out.println(calculateServiceWithoutStream.getMapByNamesLength(passengers));
        }
    }

}
