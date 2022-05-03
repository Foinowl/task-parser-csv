The purpose of the application is to get acquainted with working with streams and just 'cycles'.
When completing the task, a csv parser was written.

Now all primitive types are supported and class-wraps of primitives.

In additional, mapping of values to LocalDateTime and LocalDate is supported.


You can take values from a file using an annotation in a regular pojo class or create CsvReader instance and use his api.

## How to use it ?


## With CsvReader:

```java
        CsvReader csvReader = new CsvReader.Builder()
            .setInputStream(getClassPathResourceInputStream("train.csv"))
            .setHeading(true)
            .setSeparator(Constants.COMMA)
            .build();


        csvReader.parse();

        List<Passenger> obj = new ArrayList<>();

        Iterator<CsvReader.CsvDetails> iter = csvReader.iterator();
        ConverterPojo<Passenger> passengerConverter = new PassengerConverter();
        while (iter.hasNext()) {
            obj.add(passengerConverter.convertToObject(iter.next()));
        }
        return obj;
```

## With annotation: 

### Using annotation to mapping with custom converters
```java
public class Passenger {
    @BindColumn(position = 0)
    private long passengerId;

    @BindColumn(column = "Survived", converter = ConverterToSurvived.class)
    private Survived survived;

    @BindColumn(column = "Name")
    private String name;

    @BindColumn(column = "Sex", converter = ConverterToGender.class)
    private Gender gender;

    @BindColumn(column = "Age", converter = DoubleConverter.class)
    private Double age;
}
```

## Read csv file

```java
CsvBeanReader<Passenger> beanBuilder = new CsvBeanReader
    .Builder<Passenger>()
    .setClass(Passenger.class)
    .setInputStream(getClassPathResourceInputStream("train.csv"))
    .build();

    return beanBuilder.map();
```