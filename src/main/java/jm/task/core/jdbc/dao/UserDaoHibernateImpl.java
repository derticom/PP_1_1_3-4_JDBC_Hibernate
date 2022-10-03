package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory factory = Util.createFactory();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            String sql = """
                CREATE TABLE IF NOT EXISTS util_users (
                id SERIAL PRIMARY KEY,
                name TEXT NOT NULL,
                last_name TEXT NOT NULL,
                age INT NOT NULL
                );
            """;

//            Query query = session.createSQLQuery(sql).addEntity(User.class);
//            query.executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory factory = Util.createFactory();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            session.createQuery("delete User").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        SessionFactory factory = Util.createFactory();

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User newUser = new User(name, lastName, age);
            session.save(newUser);
            session.getTransaction().commit();
        }

    }

    @Override
    public void removeUserById(long id) {

        SessionFactory factory = Util.createFactory();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            session.createQuery("delete from User").executeUpdate();

            session.getTransaction().commit();
        }

    }

    @Override
    public List<User> getAllUsers() {

        SessionFactory factory = Util.createFactory();
        List<User> result;

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            //users = (List<User>) session.createQuery("from util_users").list();


            result = session.createQuery("from User").getResultList();

            session.getTransaction().commit();
            return result;
        }


    }

    @Override
    public void cleanUsersTable() {

        SessionFactory factory = Util.createFactory();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            session.createQuery("delete from User").executeUpdate();

            session.getTransaction().commit();
        }


    }
}
