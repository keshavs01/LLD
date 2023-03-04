package org.example.service;

import org.example.entity.CapacityHandler;
import org.example.exception.RestaurantNotFoundException;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRestaurantCapacityHandler implements RestaurantCapacityHandler {

    final ConcurrentHashMap<UUID, CapacityHandler> capacityMap;

    public InMemoryRestaurantCapacityHandler(){
        capacityMap = new ConcurrentHashMap<>();
    }


    @Override
    public void initRestaurantCapacity(UUID restaurantId, Integer maxCapacity) {
        capacityMap.putIfAbsent(restaurantId, new CapacityHandler(maxCapacity, 0));
    }

    @Override
    public void increaseCapacity(UUID restaurantId, Integer capacity) {
        CapacityHandler capacityHandler = capacityMap.get(restaurantId);
        if (capacityHandler == null) {
            throw new RestaurantNotFoundException();
        }

        synchronized (capacityHandler) {
            capacityHandler.validateAndIncreaseCapacity(capacity);
        }

    }

    @Override
    public void decreaseCapacity(UUID restaurantId, Integer capacity) {
        CapacityHandler capacityHandler = capacityMap.get(restaurantId);
        if (capacityHandler == null) {
            throw new RestaurantNotFoundException();
        }
        synchronized (capacityHandler) {
            capacityHandler.validateAndDecreaseCapacity(capacity);
        }
    }
}
