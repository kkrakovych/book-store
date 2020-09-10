package com.kosto.model;

import java.util.StringJoiner;

public final class BookDTO {

    private String name;
    private String author;
    private int quantity;

    public BookDTO() {
    }

    public BookDTO(String name, String author, int quantity) {
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BookDTO.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("author='" + author + "'")
                .add("quantity=" + quantity)
                .toString();
    }
}
