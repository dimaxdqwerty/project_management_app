package app.management.project.models.impl;

import app.management.project.models.CookieUserModel;
import app.management.project.services.CookieUserService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = CookieUserModel.class)
public class CookieUserModelImpl implements CookieUserModel {

    @OSGiService
    CookieUserService cookieUserService;

    @Override
    public String getString() {
        return cookieUserService.getString();
    }

    @PostConstruct
    protected void init() {}
}
