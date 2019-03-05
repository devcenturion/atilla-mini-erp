package cm.devcenturion.atilla.interfaces;

import cm.devcenturion.atilla.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface UserInterface{
    public ObservableList<User> USERLIST = FXCollections.observableArrayList();
}
