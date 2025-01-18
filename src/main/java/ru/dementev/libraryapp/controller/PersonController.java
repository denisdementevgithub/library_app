package ru.dementev.libraryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dementev.libraryapp.dao.BookDAO;
import ru.dementev.libraryapp.dao.PersonDAO;
import ru.dementev.libraryapp.model.Book;
import ru.dementev.libraryapp.model.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    @Autowired
    public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }
    @GetMapping
    public String showAllPeople(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        //получаем одного человека по id из dao и передаем через модели в представление
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", bookDAO.showBooksForIdPerson(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model) {
        //создаем нового человека и передаем через модель в представление для заполнения полей
        model.addAttribute("person", new Person());
        return "people/new";
    }
    @PostMapping()
    public String addPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "people/new";
        personDAO.add(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "people/edit";
        personDAO.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
