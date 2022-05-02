package org.example.titanic.mapper;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.example.titanic.model.Gender;
import org.example.titanic.parser.ConverterHandler;

public class ConverterToGender extends AbstractBeanField implements ConverterHandler {

    @Override
    public Gender convert(final String s) {
        return Gender.of(s);
    }
}
