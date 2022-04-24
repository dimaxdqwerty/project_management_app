package app.management.project.dao;

import app.management.project.beans.User;

import java.util.Iterator;
import java.util.Optional;

public interface UserDAO {

    void setUserRole(String username, String role);
    Optional<User> getUser(String username, String password);
    Optional<User> getUserForRegistration(String username);
    Optional<User> addAndGetUser(String username, String password, String role);
    String getUserRole(String username);
    Iterator<String> getAllUsernames();
    Iterator<String> getAllRoles();
}
