package com.capacitapp.models;

import java.util.List;

public class ItemData {
    private String text;
    private boolean expanded;
    private List<String> checkBoxItems;

    public ItemData(String text, List<String> checkBoxItems) {
        this.text = text;
        this.expanded = false;
        this.checkBoxItems = checkBoxItems;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public List<String> getCheckBoxItems() {
        return checkBoxItems;
    }

    public void setCheckBoxItems(List<String> checkBoxItems) {
        this.checkBoxItems = checkBoxItems;
    }
}
