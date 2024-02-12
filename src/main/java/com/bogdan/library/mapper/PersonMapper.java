package com.bogdan.library.mapper;

import com.bogdan.library.dto.PersonDTO;
import com.bogdan.library.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public Person create(PersonDTO personDTO) {
        Person person = new Person();
        person.setFullName(personDTO.getFullName());
        person.setYearOfBirth(personDTO.getYearOfBirth());
        return person;
    }
    public PersonDTO map(Person entity) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(entity.getPersonId());
        personDTO.setFullName(entity.getFullName());
        personDTO.setYearOfBirth(entity.getYearOfBirth());
        return personDTO;
    }
}
