package org.example.titanic.model;


import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.titanic.mapper.ConverterToGender;
import org.example.titanic.mapper.ConverterToSurvived;
import org.example.titanic.mapper.DoubleConverter;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Titanic implements RequestBean{

    @CsvBindByName(column = "PassengerId")
    private long passengerId;

    @CsvCustomBindByName(column = "Survived", converter = ConverterToSurvived.class)
    private Survived survived;

    @CsvBindByName(column = "Name")
    private String name;

    @CsvCustomBindByName(column = "Sex", converter = ConverterToGender.class)
    private Gender gender;

    @CsvCustomBindByName(column = "Age", converter = DoubleConverter.class)
    private Double age;
}
