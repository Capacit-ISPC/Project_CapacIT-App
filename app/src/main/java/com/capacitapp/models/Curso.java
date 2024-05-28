package com.capacitapp.models;

public class Curso {

    int id;
    String  name;
    String description;
    String language;
    String technology;
    String level;
    double  price;
    String link;
    String teacher_name;

    public Curso(){

    }

    public Curso(int id, String name, String description, String language, String technology, String level, double price, String link, String teacher_name) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.language = language;
        this.technology = technology;
        this.level = level;
        this.price = price;
        this.link = link;
        this.teacher_name = teacher_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTeacherName() {
        return teacher_name;
    }

    public void setTeacherName(String teacher_name) {
        this.teacher_name = teacher_name;
    }
}


