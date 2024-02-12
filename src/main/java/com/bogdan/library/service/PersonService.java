package com.bogdan.library.service;

import com.bogdan.library.dto.PersonDTO;

import java.util.List;

public interface PersonService {
    PersonDTO getById(int id);
    List<PersonDTO> getAll();
    PersonDTO save(PersonDTO dto);
    PersonDTO update(int id,PersonDTO dto);
    boolean delete(int id);

}
