package net.springboot.blog.model.post;

public enum Category {

    TECH("IT , Computers , Technologies"),
    NON_TECH("All other types");

    private final String categoryName;

    Category(String name) {
        this.categoryName = name;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
