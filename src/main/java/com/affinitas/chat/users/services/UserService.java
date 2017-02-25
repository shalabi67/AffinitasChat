package com.affinitas.chat.users.services;

import com.affinitas.chat.users.data.User;
import com.affinitas.chat.users.data.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mohammad on 2/25/2017.
 */
@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;

    public User getUser(int userId) {
        return usersRepository.getUserById(userId);
    }
}
