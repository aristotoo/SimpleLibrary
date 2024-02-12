package com.bogdan.library.util;

import com.bogdan.library.dao.PersonDao;
import com.bogdan.library.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return PersonDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        PersonDTO personDTO = (PersonDTO) target;

        if(personDao.getByFullName(personDTO.getFullName()).isPresent())
            errors.rejectValue("fullName","409","Такое имя уже существует");

    }
}
