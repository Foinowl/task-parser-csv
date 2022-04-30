package org.example.titanic.mapper;

import org.example.titanic.model.RequestBean;
import org.example.titanic.parser.CsvReader;

public interface ConverterPojo<T> {
    <T extends RequestBean> T convertToRead(String[] string);

    <T extends RequestBean> T convertToRead(CsvReader.CsvDetails details);
}
