package com.tonikamitv.loginregister.pushnotification;

/**
 * Created by sabrina on 2/25/2016.
 */
public class MyNotification {
    String userName;
    String event;
    String message;
    String time;

    public MyNotification(String userName, String event, String message, String time) {
        this.userName = userName;
        this.event = event;
        this.message = message;
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
