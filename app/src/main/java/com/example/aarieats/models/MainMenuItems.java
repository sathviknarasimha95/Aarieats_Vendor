package com.example.aarieats.models;

public class MainMenuItems {

    private String menuName;

    private int thumbnail;

    private String action;

    public MainMenuItems(String menuName, int thumbnail, String action) {
        this.menuName = menuName;
        this.thumbnail = thumbnail;
        this.action = action;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getAction() {
        return action;
    }
}
