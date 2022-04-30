package org.example.titanic.model;


import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Titanic implements RequestBean{

    @CsvBindByName(column = "PassengerId")
    private long passengerId;

    @CsvBindByName(column = "Survived")
    private int survied;

    @CsvBindByName(column = "Pclass")
    private int passengerClass;

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "Sex")
    private String sex;

    @CsvBindByName(column = "Age")
    private String age;

    @CsvBindByName(column = "SibSp")
    private int sibShip;

    @CsvBindByName(column = "Parch")
    private int parch;

    @CsvBindByName(column = "Ticket")
    private String ticket;

    @CsvBindByName(column = "Fare")
    private double fare;

    @CsvBindByName(column = "Cabin")
    private String cabin;

    @CsvBindByName(column = "Embarked")
    private char embarked;

    public boolean hasWoman() {
        return this.sex.equals("female");
    }

    public boolean hasMan() {
        return this.sex.equals("male");
    }

    public boolean hasSurvied() {
        return survied == 1;
    }
}
