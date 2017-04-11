package com.example.kslovic.tasky;

public class Task {
    private String tTitle, tCategory;
    int tPriority;

    public Task(String tTitle, String tCategory, int tPriority) {
        this.tTitle = tTitle;
        this.tCategory = tCategory;
        this.tPriority = tPriority;
    }

    public String gettTitle() {
        return tTitle;
    }

    public String gettCategory() {
        return tCategory;
    }

    public int gettPriority() {
        return tPriority;
    }
}
