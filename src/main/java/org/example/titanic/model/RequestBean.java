package org.example.titanic.model;

public interface RequestBean {
    Survived getSurvived();

    Gender getGender();

    Double getAge();

    String getName();

    void setPassengerId(Long longs);

    void setSurvived(Survived survived);

    void setName(String name);

    void setGender(Gender gender);

    void setAge(Double d);
}
