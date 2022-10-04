package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        try (SessionFactory factory = Util.createFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            String sql = """
                        CREATE TABLE IF NOT EXISTS util_users (
                        id SERIAL PRIMARY KEY,
                        name TEXT NOT NULL,
                        last_name TEXT NOT NULL,
                        age INT NOT NULL
                        );
                    """;
            session.createQuery("delete User").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {

        try (SessionFactory factory = Util.createFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            session.createQuery("delete User").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {


        try (SessionFactory factory = Util.createFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User newUser = new User(name, lastName, age);
            session.save(newUser);
            session.getTransaction().commit();
        }

    }

    @Override
    public void removeUserById(long id) {


        try (SessionFactory factory = Util.createFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User forDelete = session.get(User.class, id);
            session.delete(forDelete);
            session.getTransaction().commit();
        } catch (java.lang.IllegalArgumentException e) {
        }

    }

    @Override
    public List<User> getAllUsers() {

        List<User> result;

        try (SessionFactory factory = Util.createFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            result = session.createQuery("from User").getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public void cleanUsersTable() {


        try (SessionFactory factory = Util.createFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            session.createQuery("delete from User").executeUpdate();

            session.getTransaction().commit();
        }


    }
}
