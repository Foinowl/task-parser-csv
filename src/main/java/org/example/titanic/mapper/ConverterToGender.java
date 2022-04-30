package org.example.titanic.mapper;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.example.titanic.model.Gender;

public class ConverterToGender extends AbstractBeanField {

    @Override
    protected Gender convert(final String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return Gender.of(s);
    }
}
