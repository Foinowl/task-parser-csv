package org.example.titanic.model;

import com.opencsv.bean.CsvBindByName;

public class Passenger implements RequestBean{
    @CsvBindByName(column = "PassengerId")
    private long passengerId;

    @CsvBindByName(column = "Survived")
    private Survived survied;

    @CsvBindByName(column = "Pclass")
    private int passengerClass;

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "Sex")
    private Gender gender;

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

}
