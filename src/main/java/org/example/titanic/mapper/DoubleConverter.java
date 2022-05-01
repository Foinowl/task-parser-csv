package org.example.titanic.mapper;

import static org.example.titanic.utils.Utils.parseStringToDouble;
import static org.example.titanic.utils.Utils.returnDoubleWithoutNaN;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class DoubleConverter extends AbstractBeanField {

    @Override
    protected Double convert(final String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return returnDoubleWithoutNaN(parseStringToDouble(s));
    }
}
