package ru.dementev.libraryapp.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.dementev.libraryapp.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setNamebook(resultSet.getString("namebook"));
        book.setAuthor(resultSet.getString("author"));
        book.setId_person(resultSet.getInt("id_person"));
        book.setYear(resultSet.getInt("year"));
        return book;
    }
}
