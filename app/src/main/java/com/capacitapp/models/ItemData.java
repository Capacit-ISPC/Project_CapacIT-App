package com.capacitapp.models;

import java.util.List;

public class ItemData {
    private String text;
    private List<String> checkBoxItems;
    private boolean expanded;
    private Curso curso;

    public ItemData(String text, List<String> checkBoxItems, Curso curso) {
        this.text = text;
        this.checkBoxItems = checkBoxItems;
        this.curso = curso;
        this.expanded = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getCheckBoxItems() {
        return checkBoxItems;
    }

    public void setCheckBoxItems(List<String> checkBoxItems) {
        this.checkBoxItems = checkBoxItems;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
