package ru.dementev.libraryapp.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.dementev.libraryapp.model.Person;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate = DBConnection.getJdbcTemplate();

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }
    public Optional<Person> show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person LEFT JOIN Book ON Person.id = Book.id_person WHERE Person.id = ?", new Object[]{id}, new PersonMapper()).stream().findAny();
    }
    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO Person (fio, year) VALUES (?, ?)", person.getFio(), person.getDate());
    }
    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET fio = ?, year = ? WHERE id = ?", updatedPerson.getFio(), updatedPerson.getDate(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?");
    }
}
