package app.management.project.servlets;

import app.management.project.beans.User;
import app.management.project.dao.impl.UserDAOImpl;
import app.management.project.exceptions.ValidationException;
import app.management.project.utils.UserUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Optional;

import static app.management.project.constants.Constants.*;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/signin"}
)
public class LoginServlet extends SlingAllMethodsServlet {

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException {
        final String username = request.getParameter(USERNAME);
        final String password = request.getParameter(PASSWORD);
        final String linkToRedirect = request.getScheme() + COLON_WITH_TWO_SLASHES + request.getServerName()
                + COLON + request.getServerPort();
        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            Optional<User> optUser = userDAO.getUser(username, password);
            User user = optUser.orElseThrow(() -> new ValidationException(ERROR.WRONG_CREDENTIALS));

            UserUtils.setCurrentUser(username, request);
        } catch (ValidationException | RepositoryException e) {
            response.sendRedirect(linkToRedirect + CONTENT_ERROR + HTML + QUESTION_MARK + ERROR_PARAM + e.getMessage());
        }
        response.sendRedirect(linkToRedirect + CONTENT_HOME + HTML);
    }

}
