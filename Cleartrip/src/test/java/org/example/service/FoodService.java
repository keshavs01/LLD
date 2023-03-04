package org.example.service;

import org.example.data.OrderData;
import org.example.data.RestaurantData;
import org.example.data.UserData;
import org.example.entity.Location;
import org.example.entity.MenuItem;
import org.example.entity.Payment;
import org.example.exception.RestaurantAlreadyExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FoodService {

    protected UserService userService;
    protected RestaurantService restaurantService;
    protected OrderService orderService;
    protected PaymentService paymentService;

    UUID userId = UUID.randomUUID();
    UUID restaurantId = UUID.randomUUID();
    List<MenuItem> menuItemList;

    public void init() {

        UserData userData = new UserData();
        RestaurantCapacityHandler capacityHandler = new InMemoryRestaurantCapacityHandler();
        OrderData orderData = new OrderData();

        userService = new UserService(userData);
        restaurantService = new RestaurantService(new RestaurantData(), capacityHandler, orderData);
        orderService = new OrderService(orderData, capacityHandler);
        paymentService = new PaymentService(orderData, capacityHandler);

        userService.addUser(userId, "User1", "1@gmail.com");

        menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem(UUID.randomUUID(), "Samosa", 20.0, 120));
        menuItemList.add(new MenuItem(UUID.randomUUID(), "Jalebi", 30.0, 120));
        menuItemList.add(new MenuItem(UUID.randomUUID(), "Barfi", 40.0, 120));

        Location location = new Location(UUID.randomUUID(), "Haryana", "Gurgaon", 122001);

        try {
            restaurantService.addRestaurant(restaurantId,"R1", menuItemList, "Menu1", location, 10);
        } catch (Exception e) {
            //log error
            System.out.println(e);
        }
    }
}
