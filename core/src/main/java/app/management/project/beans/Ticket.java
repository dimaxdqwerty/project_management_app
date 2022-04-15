package app.management.project.beans;

public class Ticket {

    private String ticketName;
    private String shortDescription;
    private String type;

    public Ticket(String ticketName, String shortDescription, String type) {
        this.ticketName = ticketName;
        this.shortDescription = shortDescription;
        this.type = type;
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
}
