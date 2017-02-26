package com.affinitas.chat.users.services;

import com.affinitas.chat.logging.Logger;
import com.affinitas.chat.users.data.Device;
import com.affinitas.chat.users.data.DevicesRepository;
import com.affinitas.chat.users.data.User;
import com.affinitas.chat.users.data.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by mohammad on 2/25/2017.
 */
@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    DevicesRepository devicesRepository;

    public User getUser(int userId) {
        return usersRepository.getUserById(userId);
    }

    public List<Device> getUserDevices(int userId) {
        User user = usersRepository.getUserById(userId);
        if(user == null) {
            Logger.LogInfo("User not found");
            return Collections.emptyList();
        }

        return devicesRepository.getUserDevices(user);
    }
}
