package org.example.service;

import org.example.data.RestaurantData;
import org.example.entity.Restaurant;

import java.util.List;

public class LowestPriceSearchStrategy implements  SearchStrategy{


    private RestaurantData restaurantData;

    public LowestPriceSearchStrategy(RestaurantData restaurantData) {
        this.restaurantData = restaurantData;
    }

    @Override
    public List<Restaurant> search(String value) {
        List<Restaurant> restaurantList = restaurantData.getAllRestaurant();
        // fileter result
        return restaurantList;
    }
}
