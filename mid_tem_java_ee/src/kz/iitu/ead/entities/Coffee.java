package kz.iitu.ead.entities;

import java.util.Date;

public class Coffee {

    private Long id;
    private String name;
    private Users author;
    private String description;
    private int price;

    public Coffee(Long id, String name, Users author, String description,  int price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.price = price;
    }

    public Coffee(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
