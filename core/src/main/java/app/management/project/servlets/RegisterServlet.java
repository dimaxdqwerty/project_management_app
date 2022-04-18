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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static app.management.project.constants.Constants.*;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/register"}
)
public class RegisterServlet extends SlingAllMethodsServlet {

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException {
        final String username = request.getParameter(USERNAME);
        final String password = request.getParameter(PASSWORD);
        final String linkToRedirect = request.getScheme() + COLON_WITH_TWO_SLASHES + request.getServerName()
                + COLON + request.getServerPort();

        try {
            HttpSession session = request.getSession();

            UserDAOImpl userDAO = new UserDAOImpl();
            Optional<User> optUser = userDAO.addAndGetUser(username, password);
            User user = optUser.orElseThrow(() -> new ValidationException(ERROR.ACCOUNT_EXISTS));

            session.setAttribute(USER, user);
            session.setAttribute(USERNAME, user.getUsername());

            addCookie(response, user);
        } catch (ValidationException e) {
            response.sendRedirect(linkToRedirect + CONTENT_ERROR + HTML + QUESTION_MARK + ERROR_PARAM + e.getMessage());
        }

        response.sendRedirect(linkToRedirect + CONTENT_HOME + HTML);
    }

    public void addCookie(SlingHttpServletResponse response, User user) {
        Cookie cookie = new Cookie(USER_COOKIE, user.getUsername());

        //24h
        cookie.setMaxAge(86400);

        response.addCookie(cookie);
    }

}
