package com.bogdan.library.service.impl;

import com.bogdan.library.dao.PersonDao;
import com.bogdan.library.dto.PersonDTO;
import com.bogdan.library.mapper.PersonMapper;
import com.bogdan.library.model.Person;
import com.bogdan.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;
    private PersonMapper personMapper;

    @Autowired
    public PersonServiceImpl(PersonDao dao){
        this.personDao = dao;
    }

    @Autowired
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public PersonDTO getById(int id) {
        Optional<Person> byId = personDao.getById(id);
        PersonDTO result;
        if(byId.isPresent())
            result = personMapper.map(byId.get());
        else {
            result = new PersonDTO();
            result.setFullName("Not found");
            result.setYearOfBirth("None");
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDTO> getAll() {
        return personDao.getAll()
                .stream()
                .map(personMapper::map)
                .toList();
    }

    @Override
    @Transactional
    public PersonDTO save(PersonDTO dto) {
        Person person = personMapper.create(dto);
        personDao.save(person);
        return dto;
    }

    @Override
    @Transactional
    public PersonDTO update(int id, PersonDTO dto) {
        Person person = personMapper.create(dto);
        personDao.update(id,person);
        return dto;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return personDao.delete(id) > 0;
    }
}
