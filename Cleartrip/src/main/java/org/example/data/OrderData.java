package org.example.data;

import org.example.entity.Order;
import org.example.entity.Restaurant;
import org.example.exception.OrderException;
import org.example.exception.RestaurantAlreadyExistException;
import org.example.exception.RestaurantNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderData {

    private final Map<UUID, Order> orderMap;

    public OrderData(){
        orderMap = new HashMap<>();
    }

    public void addOrder(Order order){
        orderMap.put(order.getId(), order);
    }

    public Order getOrder(UUID id) throws OrderException {
        if(!orderMap.containsKey(id))
            throw new OrderException("Order not found");
        return orderMap.get(id);
    }
}
