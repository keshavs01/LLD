package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.enums.UserRoles;

public class Player extends User {

    public Player(String id, String name, String email, String color){
        super(id,name,email, UserRoles.PLAYER,0,color);
    }
}
