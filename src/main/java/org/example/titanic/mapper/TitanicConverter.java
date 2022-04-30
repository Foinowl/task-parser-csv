package org.example.titanic.mapper;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.example.titanic.model.Titanic;
import org.example.titanic.parser.CsvReader;

public class TitanicConverter implements ConverterPojo<Titanic> {


    private final DoubleConverter doubleConverter = new DoubleConverter();

    private final ConverterToSurvived converterToSurvived = new ConverterToSurvived();

    private final ConverterToGender converterToGender = new ConverterToGender();

    @Override
    public Titanic convertToRead(final String[] string) {
//        Мапим по номеру столбцу
//        Titanic titanic = new Titanic();
//        titanic.setPassengerId(Long.parseLong(string[0]);
//        return titanic;
        return null;
    }

    @Override
    public Titanic convertToRead(final CsvReader.CsvDetails details) {
        Titanic titanic = new Titanic();

        try {
            titanic.setPassengerId(Long.parseLong(details.getByColumn("PassengerId")));
            titanic.setSurvived(converterToSurvived.convert(details.getByColumn("Survived")));
            titanic.setName(details.getByColumn("Name"));
            titanic.setGender(converterToGender.convert(details.getByColumn("Sex")));
            titanic.setAge(doubleConverter.convert(details.getByColumn("Age")));
        } catch (CsvConstraintViolationException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
        return titanic;
        //        Мапим по номеру столбцу, используем getByIndex(int index);
//        Titanic titanic = new Titanic();
//        titanic.setPassengerId(Long.parseLong(details.getByColumn("PassengerId")));
//        titanic.setSurvied(Integer.parseInt(details.getByColumn("Survived")));
//        titanic.setName(details.getByColumn("Name"));
//        titanic.setSex(details.getByColumn("Sex"));
//        titanic.setAge(returnPrimitiveFromDouble(parseStringToDouble(details.getByColumn("Age"))));
//        return titanic;
    }
}
