package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItem {
    private MenuItem menuItem;
    private Integer quantity;
    private Double price;
}
