package com.example.weightlossdiary;

public class Quote {
    public String quote;
    public String author;
    public String username;
    public String userID;

    public Quote() {
    }

    public Quote(String quote, String author, String userID, String username) {
        this.quote = quote;
        this.author = author;
        this.userID = userID;
        this.username = username;
    }

    public String getTitle() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
