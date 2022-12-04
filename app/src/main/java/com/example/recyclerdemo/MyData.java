package com.example.recyclerdemo;
public class MyData {
    private String header;
    private String sub;
    private int img;

    public MyData(String header, String sub, int img) {
        this.header = header;
        this.sub = sub;
        this.img = img;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
