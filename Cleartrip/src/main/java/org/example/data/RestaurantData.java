package org.example.data;

import org.example.entity.Restaurant;
import org.example.exception.RestaurantAlreadyExistException;
import org.example.exception.RestaurantNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class RestaurantData {

    private final Map<UUID, Restaurant> restaurantMap;

    public RestaurantData(){
        restaurantMap = new HashMap<>();
    }

    public void addRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistException {
        if(restaurantMap.containsKey(restaurant.getId()))
            throw new RestaurantAlreadyExistException();
        restaurantMap.put(restaurant.getId(), restaurant);
    }

    public Restaurant getRestaurant(UUID id){
        if(!restaurantMap.containsKey(id))
            throw new RestaurantNotFoundException();
        return restaurantMap.get(id);
    }

    public List<Restaurant> getAllRestaurant(){
        return restaurantMap.values().stream().toList();
    }
}
