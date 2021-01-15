package net.springboot.blog.model.post;

public enum Category {

    TECH("Technician", "IT, Computers, Technologies"),
    BEAUTY("Beauty", "Beauty, Fashion") ,
    SPORT("Sport", "Football , Basketball") ,
    GAMES("Games", "Cyberpunk") ,
    OTHER("Other", "Other");

    private final String categoryName;
    private final String desc;

    Category(String name, String desc) {
        this.categoryName = name;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return categoryName;
    }

    public String getValue() {
        return this.name();
    }
}
