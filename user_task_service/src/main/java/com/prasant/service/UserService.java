package com.prasant.service;

import com.prasant.modal.User;

import java.util.List;

public interface UserService {

    public User getUserProfile(String jwt);

    public List<User> getAllUsers();
}
