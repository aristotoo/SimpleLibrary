package com.bogdan.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookDTO {
    private int id;
    @NotEmpty(message = "Название не должно быть пустым")
    private String title;
    @NotEmpty(message = "Поле автор не может быть пустым")
    private String author;
    private String year;
}
