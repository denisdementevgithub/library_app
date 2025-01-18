package ru.dementev.libraryapp.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Person {
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", date=" + date +
                '}';
    }

    private int id;
    @NotEmpty(message = "Введите ФИО человека")
    private String fio;
    @Min(value = 1850, message = "Введите валидный год рождения человека")
    @Max(value = 2010, message = "Человек должен быть старше 14 лет")
    private int date;

    public Person() {
    }
    public String getFio() {        return fio;    }
    public int getId() {        return id;    }
    public int getDate() {        return date;    }
    public void setDate(int date) {        this.date = date;    }
    public void setId(int id) {        this.id = id;    }
    public void setFio(String fio) {        this.fio = fio;    }

    public Person(int id, String fio, int date) {
        this.id = id;
        this.fio = fio;
        this.date = date;
    }

}
