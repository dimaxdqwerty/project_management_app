package app.management.project.comparators;

import com.day.cq.wcm.api.Page;

import java.util.Comparator;

public class PageSortByTitle implements Comparator<Page> {

    @Override
    public int compare(Page page1, Page page2) {
        return page1.getName().compareTo(page2.getName());
    }

}
