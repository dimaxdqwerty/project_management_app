package app.management.project.dao.impl;

import app.management.project.beans.User;
import app.management.project.dao.UserDAO;
import app.management.project.utils.DatabaseConnection;
import com.drew.lang.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    optUser = Optional.of(new User(rs.getString(SQL.USERNAME_COLUMN), StringUtils.EMPTY));
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
                    optUser = Optional.of(new User(rs.getString(SQL.USERNAME_COLUMN), rs.getString(SQL.PASSWORD_COLUMN)));
                }
            }
            return optUser;
        } catch(SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> addAndGetUser(String username, String password) {
        try(Connection cn = DatabaseConnection.getConnection(); PreparedStatement ps = cn.prepareStatement(SQL.INSERT_LOGIN_PASSWORD_USER)) {
            Optional<User> user = getUserForRegistration(username);
            if(!user.isPresent()) {
                ps.setString(SQL.USERNAME_COLUMN, username);
                ps.setString(SQL.PASSWORD_COLUMN, password);
                synchronized (this) {
                    ps.executeUpdate();
                }
                user = Optional.of(new User(username, password));
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
}
