package com.tonikamitv.loginregister;

/**
 * Created by sabrina on 2/1/2016.
 */
public class EventInfo {

    String name;
    String category;
    String location;
    String description;
    String dateTime;

    public EventInfo(String name, String category, String location, String description, String dateTime) {
        this.name = name;
        this.category = category;
        this.location = location;
        this.description = description;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
