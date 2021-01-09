package net.springboot.blog.model.user;

public enum Permission {

    CAN_READ("can:read") ,
    CAN_WRITE("can:write");
//    CAN_POST("can:post") ,
//    CAN_DELETE("can:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
