package app.management.project.models.impl;

import app.management.project.models.TicketInfoModel;
import app.management.project.services.TicketInfoObtainer;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
    adapters = TicketInfoModel.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TicketInfoModelImpl implements TicketInfoModel {

    @Inject
    Resource componentResource;

    @ValueMapValue
    private List<String> types;

    @ValueMapValue
    private List<String> orders;

    @OSGiService
    TicketInfoObtainer ticketInfoObtainer;

    @Override
    public Iterator<Page> getPagesList() {
        return ticketInfoObtainer.getPages();
    }

    @Override
    public List<String> getTypes() {
        if (types != null) {
            return new ArrayList<>(types);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getOrders() {
        if (orders != null) {
            return new ArrayList<>(orders);
        } else {
            return Collections.emptyList();
        }
    }

    @PostConstruct
    protected void init() {}

}
