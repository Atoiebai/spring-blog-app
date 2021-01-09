package net.springboot.blog.model.user;


import net.springboot.blog.model.user.Permission;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    USER(Set.of(Permission.CAN_READ)) ,
    ADMIN(Set.of(
            Permission.CAN_WRITE,
            Permission.CAN_READ
//            Permission.CAN_DELETE,
//            Permission.CAN_POST
    )
    );

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
