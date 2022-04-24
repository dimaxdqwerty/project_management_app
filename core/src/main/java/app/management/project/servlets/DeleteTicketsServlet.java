package app.management.project.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
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
import java.util.Iterator;

import static app.management.project.constants.Constants.*;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/deleteTickets"}
)
public class DeleteTicketsServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(DeleteTicketsServlet.class);

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
        final String resourcePath = request.getParameter(RESOURCE_PATH);

        final String linkToRedirect = request.getScheme() + COLON_WITH_TWO_SLASHES + request.getServerName()
                + COLON + request.getServerPort() + resourcePath + HTML;
        try {
            ResourceResolver resourceResolver = request.getResourceResolver();
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage(CONTENT_TICKET_STORAGE);
            Iterator<Page> pageIterator = page.listChildren();
            while (pageIterator.hasNext()) {
                Page currentPage = pageIterator.next();
                String ticketName = currentPage.getName();
                final String checkbox = request.getParameter(ticketName);
                if (checkbox != null) {
                    Node currentPageNode = currentPage.adaptTo(Node.class);
                    currentPageNode.remove();
                }
            }
            resourceResolver.commit();
            response.sendRedirect(linkToRedirect);
        } catch (RepositoryException | IOException e) {
            log.error(e.getMessage());
        }
    }
}
