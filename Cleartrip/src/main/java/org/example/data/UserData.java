package org.example.data;

import org.example.entity.Order;
import org.example.entity.User;
import org.example.exception.OrderException;
import org.example.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserData {

    private final Map<UUID, User> userMap;

    public UserData(){
        userMap = new HashMap<>();
    }

    public void addUser(User user){
        userMap.put(user.getId(), user);
    }

    public User getUser(UUID id) throws UserNotFoundException {
        if(!userMap.containsKey(id))
            throw new UserNotFoundException();
        return userMap.get(id);
    }
}
