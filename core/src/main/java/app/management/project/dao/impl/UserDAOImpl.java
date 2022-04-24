package app.management.project.dao.impl;

import app.management.project.beans.User;
import app.management.project.dao.UserDAO;
import app.management.project.utils.DatabaseConnection;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static app.management.project.constants.Constants.*;

public class UserDAOImpl implements UserDAO {

    private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {}

    @Override
    public Optional<User> getUserForRegistration(String username) {
        try (Connection cn = DatabaseConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL.SELECT_LOGIN_USER)) {
            Optional<User> optUser = Optional.empty();
            ps.setString(SQL.USERNAME_COLUMN, username);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    optUser = Optional.of(new User(rs.getString(SQL.USERNAME_COLUMN), null, StringUtils.EMPTY));
                }
            }
            return optUser;
        } catch(SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public Optional<User> getUser(String username, String password) {
        try (Connection cn = DatabaseConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL.SELECT_LOGIN_PASSWORD_USER)) {
            Optional<User> optUser = Optional.empty();
            ps.setString(SQL.USERNAME_COLUMN, username);
            ps.setString(SQL.PASSWORD_COLUMN, password);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    optUser = Optional.of(new User(rs.getString(SQL.USERNAME_COLUMN), null, rs.getString(SQL.PASSWORD_COLUMN)));
                }
            }
            return optUser;
        } catch(SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> addAndGetUser(String username, String password, String role) {
        try(Connection cn = DatabaseConnection.getConnection(); PreparedStatement ps = cn.prepareStatement(SQL.INSERT_LOGIN_PASSWORD_USER)) {
            Optional<User> user = getUserForRegistration(username);
            if(!user.isPresent()) {
                ps.setString(SQL.USERNAME_COLUMN, username);
                ps.setString(SQL.PASSWORD_COLUMN, password);
                ps.setString(SQL.ROLE_COLUMN, role);
                synchronized (this) {
                    ps.executeUpdate();
                }
                user = Optional.of(new User(username, role, password));
            }
            else {
                user = Optional.empty();
            }
            return user;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public String getUserRole(String username) {
        String role = null;
        try (Connection cn = DatabaseConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL.SELECT_USER_ROLE)) {
            ps.setString(SQL.USERNAME_COLUMN, username);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    role = rs.getString(1);
                }
            }
        } catch(SQLException e) {
            log.error(e.getMessage());
        }
        return role;
    }

    @Override
    public void setUserRole(String username, String role) {
        try (Connection cn = DatabaseConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL.UPDATE_USER_ROLE)) {
            ps.setString(1, role);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Iterator<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        try (Connection cn = DatabaseConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL.SELECT_ALL_USERNAMES)) {
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    usernames.add(rs.getString(1));
                }
            }
            return usernames.iterator();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Iterator<String> getAllRoles() {
        List<String> roles = new ArrayList<>();
        try (Connection cn = DatabaseConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL.SELECT_ALL_ROLES)) {
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    roles.add(rs.getString(1));
                }
            }
            return roles.iterator();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
