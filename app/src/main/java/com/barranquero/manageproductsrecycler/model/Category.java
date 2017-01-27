package com.barranquero.manageproductsrecycler.model;

/**
 * Created by usuario on 27/01/17
 * ManageProductsFragment
 */

public class Category {
    int id;
    String description;

    public Category() {
    }

    public Category(String description, int id) {
        this.description = description;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
