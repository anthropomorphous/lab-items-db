package com.example.itemsdb;

import androidx.annotation.NonNull;

public class ItemModel {
    private int id;
    private String itemName;
    private String itemType;
    private float cost;

    public ItemModel(int id, String itemName, String itemType, float cost) {
        this.id = id;
        this.itemName = itemName;
        this.itemType = itemType;
        this.cost = cost;
    }

    // some cute method for printing our class contents as string
    // to logs or alert or whatever

    @NonNull
    @Override
    public String toString() {
        return " ID: " + id +
                ", Товар: " + itemName +
                ", Тип товара: " + itemType +
                ", Цена: " + cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
