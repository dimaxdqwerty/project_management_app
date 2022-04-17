package app.management.project.dao;

import app.management.project.beans.User;

import java.util.Optional;

public interface UserDAO {


    Optional<User> getUser(String username, String password);
    Optional<User> getUserForRegistration(String username);
    Optional<User> addAndGetUser(String username, String password);
}
