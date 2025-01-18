package ru.dementev.libraryapp.dao;

import org.springframework.stereotype.Component;
import ru.dementev.libraryapp.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static Connection connection;
    private static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER = "postgres";
    private static String PASSWORD = "admin";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        String SQL = "SELECT * FROM Person";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setFio(resultSet.getString("fio"));
                person.setDate(resultSet.getInt("year"));
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }
    public Person show(int id) {
        Person person = new Person();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person LEFT JOIN Book ON Person.id = Book.id_person WHERE Person.id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == true) {
                person.setId(resultSet.getInt("id"));
                person.setFio(resultSet.getString("fio"));
                person.setDate(resultSet.getInt("year"));
            } else {
                System.out.println("Информационное сообщение: resultSet пустой");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
    public void add(Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Person (fio, year) VALUES (?, ?)");
            statement.setString(1, person.getFio());
            statement.setInt(2, person.getDate());
            int result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(int id, Person updatedPerson) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Person SET fio = ?, year = ? WHERE id = ?");
            statement.setString(1, updatedPerson.getFio());
            statement.setInt(2, updatedPerson.getDate());
            statement.setInt(3, id);
            int result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Person WHERE id = ?");
            statement.setInt(1, id);
            int result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
