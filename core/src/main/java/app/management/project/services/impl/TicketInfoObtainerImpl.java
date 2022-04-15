package app.management.project.services.impl;

import app.management.project.services.TicketInfoObtainer;
import app.management.project.utils.ResolverUtil;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Iterator;

import static app.management.project.constants.Constants.*;

@Component(service = TicketInfoObtainer.class, immediate = true)
@ServiceDescription("Renders all tickets from Ticket Storage in ticket-container component")
public class TicketInfoObtainerImpl implements TicketInfoObtainer {

    private static final Logger log = LoggerFactory.getLogger(TicketInfoObtainerImpl.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Reference
    private SlingRepository slingRepository;

    @Override
    public Iterator<Page> getPages() {
        try {
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage(CONTENT_TICKET_STORAGE);
            Iterator<Page> pages = page.listChildren();
            return pages;
        } catch (LoginException e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
