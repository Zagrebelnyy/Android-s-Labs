package com.example.lab6;

public class Product {
    private String name;
    private int price;
    private int count;
    private String btn;
    public Product(String name, int price, int count){
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }


    public int getPrice() {
        return price;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
