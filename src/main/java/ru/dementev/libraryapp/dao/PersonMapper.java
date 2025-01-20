package ru.dementev.libraryapp.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.dementev.libraryapp.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person= new Person();
        person.setId(resultSet.getInt("id"));
        person.setFio(resultSet.getString("fio"));
        person.setDate(resultSet.getInt("year"));
        return person;
    }
}
