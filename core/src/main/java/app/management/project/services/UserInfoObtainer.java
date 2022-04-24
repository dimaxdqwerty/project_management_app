package app.management.project.services;

import java.util.Iterator;

public interface UserInfoObtainer {
    String getUsername();
    String getRole();
    Iterator<String> getAllUsernames();
    Iterator<String> getAllRoles();
}
