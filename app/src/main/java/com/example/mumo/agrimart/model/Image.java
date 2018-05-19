package com.example.mumo.agrimart.model;

public class Image {
    private int id;
    private String img_path;

    public Image(int id, String img_path) {
        this.id = id;
        this.img_path = img_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_path() {
        String base_path = "https://hidden-headland-18384.herokuapp.com/";
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
