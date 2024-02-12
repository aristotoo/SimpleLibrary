package com.bogdan.library.controller;

import com.bogdan.library.dto.BookDTO;
import com.bogdan.library.dto.PersonDTO;
import com.bogdan.library.service.impl.BookServiceImpl;
import com.bogdan.library.service.impl.ManagerServiceImpl;
import com.bogdan.library.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private static final String BOOKS_PAGE = "redirect:/books";
    private ManagerServiceImpl managerService;
    private final BookServiceImpl bookService;
    private PersonServiceImpl personService;

    @Autowired
    public void setPersonService(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setManagerService(ManagerServiceImpl managerService) {
        this.managerService = managerService;
    }

    @GetMapping()
    public String list(Model model){
        model.addAttribute("books",bookService.getAll());
        return "book/list";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") BookDTO book){
        return "book/new";
    }

    @GetMapping("/{id}")
    public String open(@PathVariable("id") int id,Model model,@ModelAttribute("person") PersonDTO personDTO){
        model.addAttribute("book",bookService.getById(id));

        PersonDTO bookOwner = managerService.getBookOwner(id);
        if(bookOwner != null)
            model.addAttribute("owner",bookOwner);
        else
            model.addAttribute("people",personService.getAll());

        return "book/card";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid BookDTO bookDTO,
                         BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "person/new";

        bookService.save(bookDTO);
        return BOOKS_PAGE;
    }


    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookService.getById(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid BookDTO bookDTO,
                         BindingResult bindingResult, @PathVariable("id") int id){

        if(bindingResult.hasErrors())
            return "book/edit";

        bookService.update(id,bookDTO);
        return BOOKS_PAGE;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return BOOKS_PAGE;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        managerService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") PersonDTO selectedPerson){
        managerService.assign(selectedPerson.getId(),id);
        return "redirect:/books/" + id;
    }
}
