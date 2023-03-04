package org.example.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Restaurant {

    private UUID id;
    private String name;
    private Location location;
    private Integer maxCapacity;
    private Menu menu;
}
