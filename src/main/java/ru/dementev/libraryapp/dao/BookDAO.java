package ru.dementev.libraryapp.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.dementev.libraryapp.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate = DBConnection.getJdbcTemplate();
    private static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER = "postgres";
    private static String PASSWORD = "admin";
    private static Connection connection;
    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }
    public Optional<Book> show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", new Object[]{id}, new BookMapper()).stream().findAny();
    }
    public List<Book> showBooksForIdPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id_person = ?", new Object[]{id}, new BookMapper());
    }
    public void add(Book book) {
        jdbcTemplate.update("INSERT INTO Book(namebook, author, year) VALUES (?, ?, ?)", book.getNamebook(),
                book.getAuthor(), book.getYear());
    }
    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET namebook = ?, author = ?, year = ? WHERE id = ?",
                updatedBook.getNamebook(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);
    }

    public void setId_personForBook(int id, int id_person) {
        jdbcTemplate.update("UPDATE Book SET id_person = ? WHERE id = ?", id_person, id);
    }

    public void deleteId_personForBook(int id) {
        jdbcTemplate.update("UPDATE Book SET id_person = null WHERE id = ?", id);
    }
}
