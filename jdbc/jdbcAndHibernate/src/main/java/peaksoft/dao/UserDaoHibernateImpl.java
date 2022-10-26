package peaksoft.dao;

import jakarta.persistence.Query;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.HibernateUtil;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {
            session.beginTransaction();
            session.getTransaction().commit();
            System.out.println("User table was created successfully");
        }
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("A new user " + name + " was inserted into the table");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("delete User where id =: paramId");
            query.setParameter("paramId", id);
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Current id: " + id + " was deleted from the table");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("select c from User c", User.class).getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
