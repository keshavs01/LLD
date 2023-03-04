package org.example.service;

import org.example.entity.MenuItem;
import org.example.entity.Restaurant;

import java.util.List;

public interface SearchStrategy {
    List<Restaurant> search(String value);
}
