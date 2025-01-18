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
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookDAO bookDAO;
    private PersonDAO personDAO;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;    }
    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> bookList = bookDAO.index();
        model.addAttribute("books", bookList);
        return "books/index";
    }
    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model){
        Book book = bookDAO.show(id);
        model.addAttribute("book", book);
        System.out.println(book.getId_person());
        if (personDAO.show(book.getId_person()) == null) {
            model.addAttribute("people", personDAO.index());
            model.addAttribute("person", new Person());
        } else {
            model.addAttribute("person", personDAO.show(book.getId_person()));
        }
        return "books/show";
    }
    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }
    @PostMapping
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "books/new";
        bookDAO.add(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        System.out.println(book.toString());
        if(bindingResult.hasErrors()) return "books/edit";
        bookDAO.update(id, book);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/update_person_id")
    public String updatePersonId(@ModelAttribute("person") Person person,
                                 BindingResult bindingResult,
                                 @PathVariable("id") int id) {
        bookDAO.setId_personForBook(id, person.getId());
        return "redirect:/books/{id}";

    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/make_id_person_null")
    public String deleteId_PersonForBook(@PathVariable("id") int id) {
        bookDAO.deleteId_personForBook(id);
        return "redirect:/books/{id}";
    }
}
