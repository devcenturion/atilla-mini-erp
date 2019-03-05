package cm.devcenturion.atilla.service;

import cm.devcenturion.atilla.config.connectivity.HibernateUtil;
import cm.devcenturion.atilla.dao.UserDao;
import cm.devcenturion.atilla.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class UserService implements UserDao{

    private static Session session;

    public ObservableList<User> getEmployees ( ) {
        ObservableList<User> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        session.beginTransaction().commit();
        users.stream().forEach(list::add);

        return list;
    }

    public User getUser (long id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.getTransaction().commit();

        return user;
    }

    public String getUserType (String username) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();

        return user.getType();
    }

    public void saveUser (User user) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

    }

    public void updateUser (User user) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User u = (User) session.get(User.class, user.getType());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setUserName(user.getUserName());
        u.setPassword(user.getPassword());
        u.setPhone(user.getPhone());
        u.setAddress(user.getAddress());
        session.getTransaction().commit();

    }

    public void deleteEmployee (User user) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User u = (User) session.get(User.class, user.getId());
        session.delete(u);
        session.getTransaction().commit();

    }

    public boolean checkPassword (String username, String password) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();

        return user.getPassword().equals(password);
    }

    public boolean checkUser (String username) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();

        return user != null;
    }
}
