package ru.dementev.libraryapp.dao;

import org.springframework.stereotype.Component;
import ru.dementev.libraryapp.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {
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
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Book");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setNamebook(resultSet.getString("namebook"));
                book.setAuthor(resultSet.getString("author"));
                book.setId_person(resultSet.getInt("id_person"));
                book.setYear(resultSet.getInt("year"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public Book show(int id) {
        Book book = new Book();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getInt("year"));
            book.setId(resultSet.getInt("id"));
            book.setNamebook(resultSet.getString("namebook"));
            book.setAuthor(resultSet.getString("author"));
            book.setId_person(resultSet.getInt("id_person"));
            book.setYear(resultSet.getInt("year"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
    public List<Book> showBooksForIdPerson(int id) {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book WHERE id_person = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setNamebook(resultSet.getString("namebook"));
                book.setAuthor(resultSet.getString("author"));
                book.setId_person(resultSet.getInt("id_person"));
                book.setYear(resultSet.getInt("year"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public void add(Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Book(namebook, author, year) VALUES (?, ?, ?)");
            statement.setString(1, book.getNamebook());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getYear());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(int id, Book updatedBook) {
        System.out.println(updatedBook);
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Book SET namebook = ?, author = ?, year = ? WHERE id = ?");
            statement.setString(1, updatedBook.getNamebook());
            statement.setString(2, updatedBook.getAuthor());
            statement.setInt(3, updatedBook.getYear());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Book WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setId_personForBook(int id, int id_person) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Book SET id_person = ? WHERE id = ?");
            statement.setInt(1, id_person);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteId_personForBook(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Book SET id_person = null WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
