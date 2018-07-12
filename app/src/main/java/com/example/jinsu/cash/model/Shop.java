package com.example.jinsu.cash.model;

public class Shop {
    private String im_url;
    private String brand;
    private String name;
    private String point;

    public Shop(String im_url, String brand, String name, String point) {
        this.im_url = im_url;
        this.brand = brand;
        this.name = name;
        this.point = point;
    }

    public String getIm_url() {
        return im_url;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getPoint() {
        return point;
    }
}
