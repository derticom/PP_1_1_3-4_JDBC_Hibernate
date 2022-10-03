package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    /*
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
    */

    public static synchronized SessionFactory createFactory() {

//        return new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(User.class)
//                .buildSessionFactory();

        Properties prop= new Properties();
        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&amp;serverTimezone=UTC");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "125BNsd%r312");
        prop.setProperty("hibernate.current_session_context_class", "thread");
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("show_sql", "true");

        return new Configuration()
                .addProperties(prop)
                .configure()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

}
