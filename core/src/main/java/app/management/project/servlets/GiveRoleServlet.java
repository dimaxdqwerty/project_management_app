package app.management.project.servlets;

import app.management.project.dao.impl.UserDAOImpl;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;

import java.io.IOException;

import static app.management.project.constants.Constants.*;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/giveRole"}
)
public class GiveRoleServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(GiveRoleServlet.class);

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
        try {
            final String resourcePath = request.getParameter(RESOURCE_PATH);
            final String username = request.getParameter(USERNAME);
            final String role = request.getParameter(ROLE);

            UserDAOImpl userDAO = new UserDAOImpl();
            userDAO.setUserRole(username, role);

            final String linkToRedirect = request.getScheme() + COLON_WITH_TWO_SLASHES + request.getServerName()
                    + COLON + request.getServerPort() + resourcePath + HTML;

            response.sendRedirect(linkToRedirect);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
