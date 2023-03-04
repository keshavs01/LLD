package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@AllArgsConstructor
public class User {

    private UUID id;
    private String name;
    private String email;

    // other metadata;
}
