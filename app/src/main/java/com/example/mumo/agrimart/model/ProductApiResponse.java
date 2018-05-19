package com.example.mumo.agrimart.model;

import java.util.List;

public class ProductApiResponse {
    private int current_page;
    private List<Product> data;
//    private int to;
    private String next_page_url;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }


    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }
}
