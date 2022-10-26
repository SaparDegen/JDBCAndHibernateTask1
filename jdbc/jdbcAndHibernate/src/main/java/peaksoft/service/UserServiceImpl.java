package peaksoft.service;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoJdbcImpl();
    private final UserDao userDaoH = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDaoH.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoH.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoH.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoH.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoH.cleanUsersTable();
    }
}
