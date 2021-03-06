package org.example.titanic.mapper;

import org.example.titanic.model.Passenger;
import org.example.titanic.parser.CsvReader;

public class PassengerConverter implements ConverterPojo<Passenger> {
    private final DoubleConverter doubleConverter = new DoubleConverter();

    private final ConverterToSurvived converterToSurvived = new ConverterToSurvived();

    private final ConverterToGender converterToGender = new ConverterToGender();

    @Override
    public Passenger convertToObject(final String[] string) {
        return null;
    }

    @Override
    public Passenger convertToObject(final CsvReader.CsvDetails details) {
        Passenger passenger = new Passenger();

        passenger.setPassengerId(Long.parseLong(details.getByColumn("PassengerId")));
        passenger.setSurvived(converterToSurvived.convert(details.getByColumn("Survived")));
        passenger.setName(details.getByColumn("Name"));
        passenger.setGender(converterToGender.convert(details.getByColumn("Sex")));
        passenger.setAge(doubleConverter.convert(details.getByColumn("Age")));

        return passenger;
    }
}
