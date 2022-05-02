package org.example.titanic.mapper;

import static org.example.titanic.utils.Utils.parseStringToDouble;
import static org.example.titanic.utils.Utils.returnDoubleWithoutNaN;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.example.titanic.parser.ConverterHandler;

public class DoubleConverter extends AbstractBeanField implements ConverterHandler {

    @Override
    public Double convert(final String s) {
        return returnDoubleWithoutNaN(parseStringToDouble(s));
    }
}
