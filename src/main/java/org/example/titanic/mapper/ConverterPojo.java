package org.example.titanic.mapper;

import org.example.titanic.parser.CsvReader;

public interface ConverterPojo<T> {
    T convertToObject(String[] string);

    T convertToObject(CsvReader.CsvDetails details);
}
