package org.example.titanic.mapper;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.example.titanic.model.Survived;
import org.example.titanic.parser.ConverterHandler;

public class ConverterToSurvived extends AbstractBeanField implements ConverterHandler {
    @Override
    public Survived convert(final String s) {
        return Survived.of(s);
    }
}
