package com.bogdan.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class Book {
    private int bookId;
    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 2,max = 100,message = "Название должно быть в диапазоне от 2 до 100 символов")
    private String title;
    @NotEmpty(message = "Поле автор не должно быть пустым")
    @Size(min = 2,max = 100,message = "Поле автор должно быть в диапазоне от 2 до 100 символов")
    private String author;
    private String year;
}
