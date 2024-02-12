package com.bogdan.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PersonDTO {
    private int id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min=2,max = 150,message = "Имя должнобыть от 2 до 150 символов")
    private String fullName;
    @Min(value = 1900,message = "Год рождения должен быть позже 1900")
    private String yearOfBirth;
}
