package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    private Util() {
    }

    public static synchronized Connection openConnection() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.getConnectValue(URL_KEY),
                    PropertiesUtil.getConnectValue(USERNAME_KEY),
                    PropertiesUtil.getConnectValue(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
