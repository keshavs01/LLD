package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
public class Location {

    private UUID id;
    private String state;
    private String name;
    private Integer pinCode;

}
