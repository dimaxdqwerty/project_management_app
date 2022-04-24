package app.management.project.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;

import java.io.IOException;

import static app.management.project.constants.Constants.*;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/changeStatus"}
)
public class ChangeTicketStatusServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(ChangeTicketStatusServlet.class);

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
        try {
            final String status = request.getParameter(STATUS);
            final String resourcePath = request.getParameter(RESOURCE_PATH);

            final String linkToRedirect = request.getScheme() + COLON_WITH_TWO_SLASHES + request.getServerName()
                    + COLON + request.getServerPort() + resourcePath + HTML;

            changeStatus(request, resourcePath, status);

            response.sendRedirect(linkToRedirect);
        } catch (IOException | RepositoryException e) {
            log.error(e.getMessage());
        }

    }

    private void changeStatus(final SlingHttpServletRequest request, String resourcePath, String status) throws RepositoryException, PersistenceException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource(resourcePath + PATH_TO_JCR_CONTENT);
        Node node = resource.adaptTo(Node.class);
        node.setProperty(STATUS, status);
        resourceResolver.commit();
    }

}
