package cm.devcenturion.atilla.dao;

import cm.devcenturion.atilla.entities.User;
import javafx.collections.ObservableList;

public interface UserDao{

    public ObservableList<User> getEmployees();
    public User getUser(long id);
    public String getUserType(String username);
    public void saveUser(User user);
    public void updateUser(User user);
    public void deleteEmployee(User user);
    public boolean checkPassword(String username,String password);
    public boolean checkUser(String username);
}
