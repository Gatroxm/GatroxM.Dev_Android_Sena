package com.example.proyectogatroxmdev;

public class CatalogItem {
    private final String title;
    private final String category;
    private final String description;
    private final int imageResId;

    public CatalogItem(String title, String category, String description, int imageResId) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
