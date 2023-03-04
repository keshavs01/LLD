package org.example.entity;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class Menu {

    private UUID id;
    private String menuName;
    private List<MenuItem> menuItemList;
}
