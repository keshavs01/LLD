package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.enums.UserRoles;

@Data
@AllArgsConstructor
class User {
    private String id;
    private String name;
    private String email;
    private UserRoles userRole;
    private Integer position;
    private String color;
}
