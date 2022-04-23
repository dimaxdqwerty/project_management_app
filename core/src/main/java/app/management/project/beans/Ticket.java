package app.management.project.beans;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static app.management.project.constants.Constants.*;

public class Ticket {

    private String ticketName;
    private String shortDescription;
    private String type;

    private String[] usernames;

    public Ticket(String ticketName, String shortDescription, String type, String usernames) {
        this.ticketName = ticketName;
        this.shortDescription = shortDescription;
        this.type = type;
        this.usernames = usernames.split(USERNAMES_SPLITTER);
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String[] getUsernames() {
        return usernames;
    }

    public void setUsernames(String[] usernames) {
        this.usernames = usernames;
    }
}
