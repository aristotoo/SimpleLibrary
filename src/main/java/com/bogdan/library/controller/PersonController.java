package com.bogdan.library.controller;

import com.bogdan.library.dao.ManagerDao;
import com.bogdan.library.dto.PersonDTO;
import com.bogdan.library.service.impl.ManagerServiceImpl;
import com.bogdan.library.service.impl.PersonServiceImpl;
import com.bogdan.library.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {
    private static final String PEOPLE_PAGE = "redirect:/people";
    private final PersonServiceImpl service;
    private ManagerServiceImpl managerService;
    private PersonValidator validator;

    @Autowired
    public PersonController(PersonServiceImpl service) {
        this.service = service;
    }

    @Autowired
    public void setManagerDao(ManagerServiceImpl managerService) {
        this.managerService = managerService;
    }

    @Autowired
    public void setValidator(PersonValidator validator) {
        this.validator = validator;
    }

    @GetMapping()
    public String list(Model model){
        model.addAttribute("people",service.getAll());
        return "person/list";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") PersonDTO person){
        return "person/new";
    }

    @GetMapping("/{id}")
    public String open(@PathVariable("id")int id,Model model){
        model.addAttribute("person",service.getById(id));
        model.addAttribute("books",managerService.getBooksPerson(id));
        return "person/card";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid PersonDTO personDTO,
                         BindingResult bindingResult) {
        validator.validate(personDTO,bindingResult);

        if(bindingResult.hasErrors())
            return "person/new";

        service.save(personDTO);
        return PEOPLE_PAGE;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person",service.getById(id));
        return "person/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid PersonDTO personDTO,
                       BindingResult bindingResult,@PathVariable("id") int id){

        validator.validate(personDTO,bindingResult);
        if(bindingResult.hasErrors())
            return "person/edit";

        service.update(id,personDTO);
        return PEOPLE_PAGE;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        service.delete(id);
        return PEOPLE_PAGE;
    }


}
