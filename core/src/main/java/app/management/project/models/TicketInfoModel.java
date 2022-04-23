package app.management.project.models;

import com.day.cq.wcm.api.Page;

import java.util.Iterator;
import java.util.List;

public interface TicketInfoModel {

    List<String> getTypes();
    List<String> getOrders();

    Iterator<Page> getPagesList();

}
