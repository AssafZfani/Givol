package com.technology.givol.model;

public class ContestHolderModel {
    private int android_image_url;
    private String name_1;
    private String name_2;

    public ContestHolderModel() {
    }

    public ContestHolderModel(int android_image_url2, String name_12, String name_22) {
        this.android_image_url = android_image_url2;
        this.name_1 = name_12;
        this.name_2 = name_22;
    }

    public int getAndroid_image_url() {
        return this.android_image_url;
    }

    public void setAndroid_image_url(int android_image_url2) {
        this.android_image_url = android_image_url2;
    }

    public String getName_1() {
        return this.name_1;
    }

    public void setName_1(String name_12) {
        this.name_1 = name_12;
    }

    public String getName_2() {
        return this.name_2;
    }

    public void setName_2(String name_22) {
        this.name_2 = name_22;
    }
}
