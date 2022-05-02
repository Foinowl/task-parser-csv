package org.example.titanic.parser;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.example.titanic.exception.CSVException;

public class CsvBeanReader<T> {

    private final Class<T> clazz;

    private final CsvReader csvReader;

    private final Map<Field, BindColumn> CACHED = new HashMap<>();

    private CsvBeanReader(final Class<T> clazz, final CsvReader csvReader) {
        this.clazz = clazz;
        this.csvReader = csvReader;
    }


    public List<T> map() {
        loadAnnotatedFieldMap(clazz.getDeclaredFields());
        csvReader.parse();
        Iterator<CsvReader.CsvDetails> iterator = csvReader.iterator();

        List<T> lst = new ArrayList<>();
        while (iterator.hasNext()) {
            T obj = mapByColumn(iterator.next());
            lst.add(obj);
        }
        return lst;
    }

    private T mapByColumn(final CsvReader.CsvDetails next) {
        try {
            T obj = clazz.getConstructor().newInstance();

            String value;
            for (Map.Entry<Field, BindColumn> entry : CACHED.entrySet()) {

                value = extractedValue(next, entry.getValue());

                setValue(entry.getKey(), entry.getValue(), obj, value);
            }
            return obj;

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void setValue(final Field field, final BindColumn annotation, final T obj, final String value) {
        Class<? extends ConverterHandler> converter = annotation.converter();

        boolean isInterface = converter.isInterface();

        Class<?> type = field.getType();

        Function<String, Object> functionConverter;

        try {
            if (!isInterface) {
                ConverterHandler objConverter = converter.getConstructor().newInstance();
                functionConverter = objConverter::convert;
            } else if (type.isAssignableFrom(String.class)) {
                functionConverter = String::valueOf;
            } else if (type.isAssignableFrom(Double.class)) {
                functionConverter = Double::valueOf;
            } else if (type.isAssignableFrom(Integer.class)) {
                functionConverter = Integer::valueOf;
            } else if (type.isAssignableFrom(Boolean.class)) {
                functionConverter = Boolean::valueOf;
            } else if (type.isAssignableFrom(LocalDateTime.class)) {
                functionConverter = LocalDateTime::parse;
            } else if (type.isAssignableFrom(LocalDate.class)) {
                functionConverter = LocalDate::parse;
            } else if (type.isAssignableFrom(Long.class)) {
                functionConverter = Long::valueOf;
            } else if (type.isAssignableFrom(Float.class)) {
                functionConverter = Float::valueOf;
            } else if (type.isAssignableFrom(Short.class)) {
                functionConverter = Short::valueOf;
            } else if (type.isAssignableFrom(Byte.class)) {
                functionConverter = Byte::valueOf;
            } else {
//                This handle cast string to primitive data;
                try {
                    if (type.isAssignableFrom(long.class)) {
                        setField(field, obj, Long.parseLong(value));
                    } else if (type.isAssignableFrom(int.class)) {
                        setField(field, obj, Integer.parseInt(value));
                    } else if (type.isAssignableFrom(double.class)) {
                        setField(field, obj, Double.parseDouble(value));
                    } else if (type.isAssignableFrom(float.class)) {
                        setField(field, obj, Float.parseFloat(value));
                    } else if (type.isAssignableFrom(byte.class)) {
                        setField(field, obj, Byte.parseByte(value));
                    } else if (type.isAssignableFrom(short.class)) {
                        setField(field, obj, Short.parseShort(value));
                    }
                    return;
                } catch (Exception e) {
                    throw new CSVException("Could not be casted to the value type - " + value, e);
                }
            }
            setField(field, obj, value == null ? null : functionConverter.apply(value));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void setField(Field field, T obj, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String extractedValue(final CsvReader.CsvDetails next, final BindColumn annotation) {
        String column = annotation.column();
        int position = annotation.position();
        boolean isEmpty = column.isEmpty();
        if (isEmpty && annotation.position() == -1) {
            throw new CSVException("One the values for annotation must be filled");
        }
        return isEmpty ? next.getByIndex(position) :
            next.getByColumn(column);
    }

    private void loadAnnotatedFieldMap(final Field[] fields) {
        Iterator<Field> iterator = Arrays.stream(fields).iterator();
        Field field;
        while (iterator.hasNext()) {
            field = iterator.next();
            if (field.isAnnotationPresent(BindColumn.class)) {
                CACHED.put(field, field.getAnnotation(BindColumn.class));
            }
        }
    }

    public static class Builder<T> {

        private Class<T> clazz;

        private InputStream inputStream;

        public Builder<T> setClass(Class<T> clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder<T> setInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
            return this;
        }

        public CsvBeanReader<T> build() {
            CsvReader csvReader = new CsvReader
                .Builder()
                .setInputStream(inputStream)
                .setHeading(true)
                .build();

            return new CsvBeanReader<>(clazz, csvReader);
        }
    }
}
