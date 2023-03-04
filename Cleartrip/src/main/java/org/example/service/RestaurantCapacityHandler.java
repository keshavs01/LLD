package org.example.service;

import java.util.UUID;

public interface RestaurantCapacityHandler {


    void initRestaurantCapacity(UUID restaurantId, Integer maxCapacity);
    void increaseCapacity(UUID restaurantId, Integer capacity);

    void decreaseCapacity(UUID restaurantId, Integer capacity);

}
