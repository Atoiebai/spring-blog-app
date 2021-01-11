package net.springboot.blog.model.user;

public enum Sex {
    MALE("Man"),
    FEMALE("Woman");

    private final String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return sex;
    }
}
