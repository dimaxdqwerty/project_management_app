package app.management.project.services;

import com.day.cq.wcm.api.Page;

import java.util.Iterator;

public interface TicketInfoObtainer {
    Iterator<Page> getPages();
    Iterator<Page> getPagesSortedByTitle();
}
