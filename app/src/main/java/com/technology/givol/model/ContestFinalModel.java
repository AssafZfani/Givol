package com.technology.givol.model;

public class ContestFinalModel {
    private String series_id;
    private int android_image_url;
    private String name_1;
    private String name_2;

    public ContestFinalModel() {
    }

    public ContestFinalModel(String series_id, int android_image_url2, String name_12, String name_22) {
        this.series_id = series_id;
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

    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }
}
