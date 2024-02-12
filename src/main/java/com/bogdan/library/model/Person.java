package com.bogdan.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class Person {
    private int personId;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min=2,max = 150,message = "Имя должнобыть от 2 до 15 символов")
    private String fullName;
    @Min(value = 1900,message = "Год рождения должен быть позже 1900")
    private String yearOfBirth;

    public Person(String fullName, String yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }
}
