package app.management.project.servlets;

import app.management.project.beans.Ticket;
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
        value = {"/bin/createTicket"}
)
public class CreateTicketServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(CreateTicketServlet.class);

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException {
        try {
            ResourceResolver resourceResolver = request.getResourceResolver();

            final String ticketName = request.getParameter(TICKET_NAME);
            final String type = request.getParameter(TYPE);
            final String shortDescription = request.getParameter(SHORT_DESCRIPTION);
            final String usernames = request.getParameter(USERNAMES);

            Ticket ticket = new Ticket(ticketName, shortDescription, type, usernames);

            createTicketPageNode(resourceResolver, ticket);

            final String pagePath = request.getParameter(RESOURCE_PATH);
            final String linkToRedirect = request.getScheme() + COLON_WITH_TWO_SLASHES + request.getServerName()
                    + COLON + request.getServerPort() + pagePath + HTML;;

            response.sendRedirect(linkToRedirect);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
    }

    private void createTicketPageNode(ResourceResolver resourceResolver, Ticket ticket) throws PersistenceException, RepositoryException {
        Resource resource = resourceResolver.getResource(CONTENT_TICKET_STORAGE);
        Node node = resource.adaptTo(Node.class);
        Node ticketPageNode = node.addNode(ticket.getTicketName(), CQ_PAGE);

        createJcrContentUnderTicketPageNode(resourceResolver, ticketPageNode, ticket);

        resourceResolver.commit();
    }

    private void createJcrContentUnderTicketPageNode(ResourceResolver resourceResolver, Node node, Ticket ticket) throws RepositoryException, PersistenceException {
        Node jcrContentUnderPageNode = node.addNode(JCR_CONTENT, CQ_PAGE_CONTENT);

        jcrContentUnderPageNode.setProperty(SLING_RESOURCE_TYPE, ASSET_SHARE_COMMONS_PAGE);
        jcrContentUnderPageNode.setProperty(JCR_TITLE, ticket.getTicketName());
        jcrContentUnderPageNode.setProperty(PAGE_TITLE, ticket.getTicketName());
        jcrContentUnderPageNode.setProperty(SHORT_DESCRIPTION, ticket.getShortDescription());
        jcrContentUnderPageNode.setProperty(TYPE, ticket.getType());
        jcrContentUnderPageNode.setProperty(CQ_TEMPLATE, ASSET_SHARE_COMMONS_EMPTY_TEMPLATE_DARK);
        jcrContentUnderPageNode.setProperty(USERNAMES, ticket.getUsernames());

        resourceResolver.commit();
    }
}
