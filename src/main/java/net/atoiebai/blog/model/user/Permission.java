package net.atoiebai.blog.model.user;

public enum Permission {

    CAN_READ("can:read") ,
    CAN_WRITE("can:write"),
    CAN_POST("can:post") ,
    CAN_DELETE("can:delete"),
    CAN_MANAGE_USERS("can:manage:users"),
    CAN_MANAGE_ADMINS("can:manage:admins")
;
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public static Permission[] getModerPermissions() {
        return new Permission[] {
                CAN_READ ,
                CAN_DELETE ,
                CAN_POST ,
                CAN_WRITE ,
                CAN_MANAGE_USERS};
    }

    public static Permission[] getUserPermissions() {
        return new Permission[] {
                CAN_READ ,
                CAN_DELETE ,
                CAN_POST ,
                CAN_WRITE };
    }

}
