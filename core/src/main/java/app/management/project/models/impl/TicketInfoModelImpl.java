package app.management.project.models.impl;

import app.management.project.models.TicketInfoModel;
import app.management.project.services.TicketInfoObtainer;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Iterator;

@Model(adaptables = SlingHttpServletRequest.class,
    adapters = TicketInfoModel.class)
public class TicketInfoModelImpl implements TicketInfoModel {

    @Inject
    @Optional
    private String option;

    @OSGiService
    TicketInfoObtainer ticketInfoObtainer;

    @Override
    public Iterator<Page> getPagesList() {
        System.out.println(option);
        return ticketInfoObtainer.getPages();
    }

    @Override
    public Iterator<Page> getPagesSortedByTitle() {
        return ticketInfoObtainer.getPagesSortedByTitle();
    }

    @PostConstruct
    protected void init() {}

}
