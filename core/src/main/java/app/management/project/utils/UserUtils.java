package app.management.project.utils;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.*;

import static app.management.project.constants.Constants.*;

@Component(service = UserUtils.class, immediate = true)
public class UserUtils {

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    public static void setCurrentUser(final String username, final String role, final SlingHttpServletRequest request) throws RepositoryException, PersistenceException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource(CONTENT_PROJECT_MANAGEMENT_APP);

        Node node = resource.adaptTo(Node.class);;
        Node currentUserNode;
        Node jcrContentUnderCurrentUserNode;
        try {
            currentUserNode = node.getNode(USER);
            jcrContentUnderCurrentUserNode = currentUserNode.getNode(JCR_CONTENT);
        } catch (PathNotFoundException e) {
            currentUserNode = node.addNode(USER, CQ_PAGE);
            jcrContentUnderCurrentUserNode = currentUserNode.addNode(JCR_CONTENT, CQ_PAGE_CONTENT);
        }
        jcrContentUnderCurrentUserNode.setProperty(USERNAME, username);
        jcrContentUnderCurrentUserNode.setProperty(ROLE, role);
        resourceResolver.commit();
    }

    public static String getCurrentUser(final ResourceResolver resourceResolver) throws RepositoryException {
        Resource resource = resourceResolver.getResource(CONTENT_USER_JCR);
        Node node = resource.adaptTo(Node.class);;
        Property username = node.getProperty(USERNAME);
        return username.getString();
    }

    public static String getCurrentUsersRole(final ResourceResolver resourceResolver) throws RepositoryException {
        Resource resource = resourceResolver.getResource(CONTENT_USER_JCR);
        Node node = resource.adaptTo(Node.class);;
        Property role = node.getProperty(ROLE);
        return role.getString();
    }

    public static void deleteCurrentUser(final SlingHttpServletRequest request) throws RepositoryException, PersistenceException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource(CONTENT_USER);
        Node node = resource.adaptTo(Node.class);
        node.remove();
        resourceResolver.commit();
    }

}
