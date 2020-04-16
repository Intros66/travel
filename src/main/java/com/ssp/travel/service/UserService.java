package com.ssp.travel.service;

import com.ssp.travel.entity.User;

public interface UserService {
    void register(User user);

    User login(User user);
}
