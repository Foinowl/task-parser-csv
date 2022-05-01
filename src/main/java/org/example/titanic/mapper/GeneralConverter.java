package org.example.titanic.mapper;

import java.util.function.Supplier;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.example.titanic.model.Passenger;
import org.example.titanic.model.RequestBean;
import org.example.titanic.parser.CsvReader;

public class GeneralConverter<T> implements ConverterPojo<T>{

    private final DoubleConverter doubleConverter = new DoubleConverter();

    private final ConverterToSurvived converterToSurvived = new ConverterToSurvived();

    private final ConverterToGender converterToGender = new ConverterToGender();

    private Supplier<T> supplier;

    public GeneralConverter(final Supplier<T> supplier) {
        this.supplier = supplier;
    }

    private T createInstance() {
        return supplier.get();
    }

    @Override
    public <T extends RequestBean> T convertToRead(final String[] string) {

    return null;
    }

    @Override
    public <T extends RequestBean> T convertToRead(final CsvReader.CsvDetails details) {
        T instanceConv = (T) createInstance();

        try {
            instanceConv.setPassengerId(Long.parseLong(details.getByColumn("PassengerId")));
            instanceConv.setSurvived(converterToSurvived.convert(details.getByColumn("Survived")));
            instanceConv.setName(details.getByColumn("Name"));
            instanceConv.setGender(converterToGender.convert(details.getByColumn("Sex")));
            instanceConv.setAge(doubleConverter.convert(details.getByColumn("Age")));
        } catch (CsvConstraintViolationException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
        return (T) instanceConv;
    }
}
