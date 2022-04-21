package app.management.project.servlets;

import app.management.project.utils.UserUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;

import java.io.IOException;

import static app.management.project.constants.Constants.*;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/logout"}
)
public class LogoutServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException {
        final String linkToRedirect = request.getScheme() + COLON_WITH_TWO_SLASHES + request.getServerName()
                + COLON + request.getServerPort();
        try {
            UserUtils.deleteCurrentUser(request);
        } catch (RepositoryException e) {
            response.sendRedirect(linkToRedirect + CONTENT_ERROR + HTML + QUESTION_MARK + ERROR_PARAM + e.getMessage());
        }
        response.sendRedirect(linkToRedirect + CONTENT_HOME + HTML);
    }
}
