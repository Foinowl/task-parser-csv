package org.example.titanic.mapper;

import static org.example.titanic.utils.Utils.parseStringToDouble;
import static org.example.titanic.utils.Utils.returnPrimitiveFromDouble;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class DoubleConverter extends AbstractBeanField {

    @Override
    protected Double convert(final String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return returnPrimitiveFromDouble(parseStringToDouble(s));
    }
}
