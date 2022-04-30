package org.example.titanic.mapper;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.example.titanic.model.Survived;

public class ConverterToSurvived extends AbstractBeanField {
    @Override
    protected Survived convert(final String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return Survived.of(s);
    }
}
