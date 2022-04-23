package app.management.project.services.impl;

import app.management.project.services.UserInfoObtainer;
import app.management.project.utils.ResolverUtil;
import app.management.project.utils.UserUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;

@Component(service = UserInfoObtainer.class, immediate = true)
@ServiceDescription("Service to get info from user for user-menu component")
public class UserInfoObtainerImpl implements UserInfoObtainer {

    private static final Logger log = LoggerFactory.getLogger(UserInfoObtainer.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Reference
    private SlingRepository slingRepository;

    @Override
    public String getUsername() {
        try {
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            return UserUtils.getCurrentUser(resourceResolver);

        } catch (LoginException | RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String getRole() {
        try {
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            return UserUtils.getCurrentUsersRole(resourceResolver);
        } catch (LoginException | RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
