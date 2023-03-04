package org.example.service;

import org.example.data.OrderData;
import org.example.data.RestaurantData;
import org.example.entity.*;
import org.example.exception.OrderException;
import org.example.exception.RestaurantAlreadyExistException;

import java.util.List;
import java.util.UUID;

public class RestaurantService {

    private RestaurantData restaurantData;
    private RestaurantCapacityHandler capacityHandler;
    private OrderData orderData;

    RestaurantService(RestaurantData restaurantData, RestaurantCapacityHandler capacityHandler, OrderData orderData){
        this.restaurantData = restaurantData;
        this.capacityHandler = capacityHandler;
        this.orderData = orderData;
    }

    public void addRestaurant(UUID restId, String name, List<MenuItem> menuItemList, String menuName, Location location, Integer maxCapacity) throws RestaurantAlreadyExistException {
        Restaurant restaurant = Restaurant.builder()
                .id(restId)
                .location(location)
                .name(name)
                .maxCapacity(maxCapacity)
                .menu(new Menu(UUID.randomUUID(),menuName, menuItemList))
                .build();
        restaurantData.addRestaurant(restaurant);
        capacityHandler.initRestaurantCapacity(restaurant.getId(), restaurant.getMaxCapacity());
    }

    public void updateRestaurant(){
        //
    }

    public void confirmOrder(UUID orderId) throws OrderException {
        Order order = orderData.getOrder(orderId);
        order.processingOrder();
    }
}
