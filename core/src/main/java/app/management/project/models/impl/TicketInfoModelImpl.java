package app.management.project.models.impl;

import app.management.project.models.TicketInfoModel;
import app.management.project.services.TicketInfoObtainer;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import java.util.Iterator;

@Model(adaptables = SlingHttpServletRequest.class,
    adapters = TicketInfoModel.class)
public class TicketInfoModelImpl implements TicketInfoModel {

    @OSGiService
    TicketInfoObtainer ticketInfoObtainer;

    @Override
    public Iterator<Page> getPagesList() {
        return ticketInfoObtainer.getPages();
    }

    @PostConstruct
    protected void init() {

    }

}
