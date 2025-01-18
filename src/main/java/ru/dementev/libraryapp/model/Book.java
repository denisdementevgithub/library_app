package ru.dementev.libraryapp.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


public class Book {
    private int id;
    @NotEmpty(message = "Введите наименование книги")
    private String namebook;
    @NotEmpty(message = "Введите имя автора")
    private String author;
    private int id_person;

    private int year;

    public Book(int id, String namebook, String author, int year) {
        this.id = id;
        this.namebook = namebook;
        this.author = author;
        this.year = year;
    }
    public Book() {
    }

    public int getId() {        return id;    }
    public void setId(int id) {        this.id = id;    }
    public String getNamebook() {        return namebook;    }
    public void setNamebook(String namebook) {        this.namebook = namebook;    }
    public String getAuthor() {        return author;    }
    public void setAuthor(String author) {        this.author = author;    }
    public int getYear() {        return year;    }
    public void setYear(int year) {        this.year = year;    }
    public int getId_person() {        return id_person;    }
    public void setId_person(int id_person) {        this.id_person = id_person;    }

}
