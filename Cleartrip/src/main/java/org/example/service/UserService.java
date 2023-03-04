package org.example.service;

import org.example.data.UserData;
import org.example.entity.User;
import org.example.exception.UserNotFoundException;

import java.util.UUID;

public class UserService {

    private UserData userData;

    public UserService(UserData userData) {
        this.userData = userData;
    }

    public void addUser(UUID userId, String name, String email){
        userData.addUser(new User(userId, name, email));
    }

    public User getUser(UUID id) throws UserNotFoundException {
        return userData.getUser(id);
    }

}
