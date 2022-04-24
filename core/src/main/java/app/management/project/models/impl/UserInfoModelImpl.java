package app.management.project.models.impl;

import app.management.project.models.UserInfoModel;
import app.management.project.services.UserInfoObtainer;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static app.management.project.constants.Constants.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = UserInfoModel.class)
public class UserInfoModelImpl implements UserInfoModel {

    @OSGiService
    UserInfoObtainer userInfoObtainer;

    @Override
    public String getUsername() {
        return userInfoObtainer.getUsername();
    }

    @Override
    public String getRole() {
        return userInfoObtainer.getRole();
    }

    @Override
    public Iterator<String> getAllUsernames() {
        return userInfoObtainer.getAllUsernames();
    }

    @Override
    public Iterator<String> getAllRoles() {
        return userInfoObtainer.getAllRoles();
    }

    @Override
    public Iterator<String> getAllUsersAndTheirsRoles() {
        Iterator<String> usernames = userInfoObtainer.getAllUsernames();
        Iterator<String> roles = userInfoObtainer.getAllRoles();
        List<String> combinedIterator = new ArrayList<>();
        while (usernames.hasNext() && roles.hasNext()) {
            combinedIterator.add(usernames.next() + ITERATORS_DIVIDER + roles.next());
        }
        return combinedIterator.iterator();
    }

    @PostConstruct
    protected void init() {}
}
