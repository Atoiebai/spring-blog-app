package net.atoiebai.blog.model.user;

public enum Sex {
    MALE("Man"),
    FEMALE("Woman");

    private final String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return sex;
    }

    public String getValue() {
        return this.name();
    }
}
