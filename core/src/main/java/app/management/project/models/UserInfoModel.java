package app.management.project.models;

import java.util.Iterator;

public interface UserInfoModel {
    String getUsername();
    String getRole();
    Iterator<String> getAllUsernames();
    Iterator<String> getAllRoles();
    Iterator<String> getAllUsersAndTheirsRoles();
}
