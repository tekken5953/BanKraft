package com.example.bankraft.Notification;

public class NotificationRecyclerItem {
    private String io;
    private String content;
    private String price;
    private String date;

    public String getIo() {
        return io;
    }

    public void setIo(String io) {
        this.io = io;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public NotificationRecyclerItem(String io, String content, String price, String date) {
        this.io = io;
        this.content = content;
        this.price = price;
        this.date = date;
    }
}
