package org.example.titanic.mapper;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.example.titanic.model.Passenger;
import org.example.titanic.model.RequestBean;
import org.example.titanic.parser.CsvReader;

public class PassengerConverter implements ConverterPojo<Passenger> {
    private final DoubleConverter doubleConverter = new DoubleConverter();

    private final ConverterToSurvived converterToSurvived = new ConverterToSurvived();

    private final ConverterToGender converterToGender = new ConverterToGender();

    @Override
    public <T extends RequestBean> T convertToRead(final String[] string) {
        return null;
    }

    @Override
    public <T extends RequestBean> T convertToRead(final CsvReader.CsvDetails details) {
        Passenger passenger = new Passenger();

        try {
            passenger.setPassengerId(Long.parseLong(details.getByColumn("PassengerId")));
            passenger.setSurvived(converterToSurvived.convert(details.getByColumn("Survived")));
            passenger.setName(details.getByColumn("Name"));
            passenger.setGender(converterToGender.convert(details.getByColumn("Sex")));
            passenger.setAge(doubleConverter.convert(details.getByColumn("Age")));
        } catch (CsvConstraintViolationException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
        return (T) passenger;
    }
}
