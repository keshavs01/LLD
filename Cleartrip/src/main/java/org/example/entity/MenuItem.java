package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class MenuItem {

    private UUID id;
    private String name;
    private Double price;
    private Integer preparationSeconds;

}
