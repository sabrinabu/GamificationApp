package com.tonikamitv.loginregister;

public class EventInfo {

    String name; //name of the event
    String category;
    String location;
    String description;
    String dateTime;
    String creator;
    String participant;


    public EventInfo(String name, String category, String location, String description, String dateTime, String creator, String participant) {
        this.name = name;
        this.category = category;
        this.location = location;
        this.description = description;
        this.dateTime = dateTime;
        this.creator = creator;
        this.participant = participant;

    }

    public String getName() {
        return name;
    }

    public String getCreatorName() {
        return creator;
    }

    public String getParticipant() {
        return participant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatorName(String creator) {
        this.creator = creator;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
