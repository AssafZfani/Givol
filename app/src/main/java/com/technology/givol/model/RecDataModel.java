package com.technology.givol.model;

public class RecDataModel {
    private String android_image_url;
    private String title;
    private String amount;
    private String participants;

    public RecDataModel() {

    }

    public RecDataModel(String amount) {
        this.amount = amount;
    }

    public RecDataModel(String android_image_url, String title, String amount, String participants) {
        this.android_image_url = android_image_url;
        this.title = title;
        this.amount = amount;
        this.participants = participants;
    }

    public String getAndroid_image_url() {
        return android_image_url;
    }

    public void setAndroid_image_url(String android_image_url) {
        this.android_image_url = android_image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }
}
