package app.management.project.servlets;

import app.management.project.beans.User;
import app.management.project.utils.ResolverUtil;
import com.adobe.cq.account.api.AccountManagementService;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static app.management.project.constants.Constants.*;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/register"}
)
public class RegisterServlet extends SlingAllMethodsServlet {

/*    @Reference
    private AccountManagementService accountManagementService;

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    private static final Logger log = LoggerFactory.getLogger(RegisterServlet.class);

    private Session session;*/

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException {
        final String username = request.getParameter(USERNAME);
        final String password = request.getParameter(PASSWORD);
        final String email = request.getParameter(EMAIL);

        String url = "jdbc:postgresql://localhost:5432/project-management-app";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","admin");
        props.setProperty("ssl","true");
        try {
            Connection conn = DriverManager.getConnection(url, props);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String uwrl = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
        try {
            Connection conn = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /*JSONObject responseObj  = new JSONObject();
        boolean isAccountCreated = false;

        User user = new User(username, password, email);

        try {
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);

            UserManager userManager = resourceResolver.adaptTo(UserManager.class);
            Authorizable userObj = userManager.getAuthorizable(username);

            if (userObj == null) {
                isAccountCreated = accountManagementService.requestAccount(username, password,
                        request.getRequestParameterMap(), request.getScheme() + COLON_WITH_TWO_SLASHES + request.getServerName()
                                + COLON + request.getServerPort(), "/etc/properties");
                responseObj.put("status", isAccountCreated);
            } else {
                responseObj.put("userExists", true);
            }

            final String linkToRedirect = request.getScheme() + COLON_WITH_TWO_SLASHES + request.getServerName()
                    + COLON + request.getServerPort() + CONTENT_HOME + HTML;
            response.sendRedirect(linkToRedirect);
        } catch (LoginException | RepositoryException | JSONException e) {
            log.error(e.getMessage());
        }*/
    }

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {

    }
}
