package app.management.project.servlets;

import app.management.project.beans.User;
import app.management.project.dao.impl.UserDAOImpl;
import app.management.project.exceptions.ValidationException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import javax.servlet.Servlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static app.management.project.constants.Constants.*;

@Component(service = Servlet.class, immediate = true)
@SlingServletPaths(
        value = {"/bin/login"}
)
public class LoginServlet extends SlingAllMethodsServlet {

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException {
        final String username = request.getParameter(USERNAME);
        final String password = request.getParameter(PASSWORD);
        final String linkToRedirect = request.getScheme() + COLON_WITH_TWO_SLASHES + request.getServerName()
                + COLON + request.getServerPort();
        try {

            HttpSession session = request.getSession();

            UserDAOImpl userDAO = new UserDAOImpl();
            Optional<User> optUser = userDAO.getUser(username, password);
            User user = optUser.orElseThrow(() -> new ValidationException(ERROR.WRONG_CREDENTIALS));

            session.setAttribute(USER, user);
            session.setAttribute(USERNAME, user.getUsername());
        } catch (ValidationException e) {
            response.sendRedirect(linkToRedirect + CONTENT_ERROR + HTML + QUESTION_MARK + ERROR_PARAM + e.getMessage());
        }
    }

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {}

}
