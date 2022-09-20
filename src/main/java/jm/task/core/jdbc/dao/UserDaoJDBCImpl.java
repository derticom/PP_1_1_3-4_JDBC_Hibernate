package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String queryRemoveUserById = """
            DELETE FROM util_users
            WHERE id = ?
            """;

    private static final String queryCreateUsersTable = """
                CREATE TABLE IF NOT EXISTS util_users (
                id SERIAL PRIMARY KEY,
                name TEXT NOT NULL,
                last_name TEXT NOT NULL,
                age INT NOT NULL
                );
            """;

    private static final String queryDropUsersTable = "DROP TABLE IF EXISTS util_users;";
    private static final String querySaveUser = """
                INSERT INTO util_users (name, last_name, age)
                VALUES (?, ?, ?);
            """;
    private static final String queryCleanUsersTable = "TRUNCATE TABLE util_users;";

    private static final UserDaoJDBCImpl INSTANCE = new UserDaoJDBCImpl();

    // 1 Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
    public void createUsersTable() {
        try (Connection connection = Util.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryCreateUsersTable)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 2 Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
    public void dropUsersTable() {
        try (Connection connection = Util.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryDropUsersTable)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 3 Очистка содержания таблицы
    public void cleanUsersTable() {
        try (Connection connection = Util.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryCleanUsersTable)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 4 Добавление User в таблицу
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(querySaveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 5  Удаление User из таблицы ( по id )
    public void removeUserById(long id) {
        try (Connection connection = Util.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryRemoveUserById)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 6 Получение всех User(ов) из таблицы
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, last_name, age FROM util_users")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                userList.add(new User(resultSet.getString(1), resultSet.getString(2), (byte) resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
}
