package app.management.project.models.impl;

import app.management.project.models.UserInfoModel;
import app.management.project.services.UserInfoObtainer;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

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

    @PostConstruct
    protected void init() {}
}
