package com.tonikamitv.loginregister;

public class User {

    String name, username, password, knowlanguage, wantlanguage, interests, cast, image;
    int age;


    public User(String name, int age, String username, String password, String knowlanguage, String wantlanguage, String interests, String cast) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.knowlanguage = knowlanguage;
        this.wantlanguage = wantlanguage;
        this.interests = interests;
        this.cast = cast;
    }

    public User(String username, String password) {
        this("", -1, username, password, "", "", "", "");
    }

    public User(String username, String password, String knowlanguage, String wantlanguage,String interests ) {
        this("", -1, username, password, knowlanguage, wantlanguage, interests, "");
    }

}
