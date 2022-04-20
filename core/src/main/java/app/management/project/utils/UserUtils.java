package app.management.project.utils;

import app.management.project.beans.User;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

import static app.management.project.constants.Constants.*;

public class UserUtils {

    public static void setCurrentUser(final String username, final SlingHttpServletRequest request) throws RepositoryException, PersistenceException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource(CONTENT_PROJECT_MANAGEMENT_APP);
        Node node = resource.adaptTo(Node.class);
        Node currentUserNode = node.getNode(USER);
        Node jcrContentUnderCurrentUserNode;

        if (currentUserNode == null) {
            currentUserNode = node.addNode(USER, CQ_PAGE);
            jcrContentUnderCurrentUserNode = currentUserNode.addNode(JCR_CONTENT, CQ_PAGE_CONTENT);
        } else {
            jcrContentUnderCurrentUserNode = currentUserNode.getNode(JCR_CONTENT);
        }

        jcrContentUnderCurrentUserNode.setProperty(USERNAME, username);
        resourceResolver.commit();
    }

}
