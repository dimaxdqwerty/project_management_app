package app.management.project.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import static app.management.project.constants.Constants.*;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/register"}
)
public class RegisterServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(RegisterServlet.class);

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException {
        final String username = request.getParameter(USERNAME);
        final String password = request.getParameter(PASSWORD);
        final String email = request.getParameter(EMAIL);

        final String linkToRedirect = request.getScheme() + COLON_WITH_TWO_SLASHES + request.getServerName()
                + COLON + request.getServerPort() + CONTENT_HOME + HTML;;

        response.sendRedirect(linkToRedirect);
    }
}
