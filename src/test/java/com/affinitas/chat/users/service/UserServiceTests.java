package com.affinitas.chat.users.service;

import com.affinitas.chat.users.data.Device;
import com.affinitas.chat.users.data.User;
import com.affinitas.chat.users.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by mohammad on 2/26/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceTests {
    @Autowired
    UserService userService;

    @Test
    public void testNonExitingUser() {
        User user= userService.getUser(-1);
        Assert.isNull(user);
    }

    @Test
    public void testGetDevicesForNonExitingUser() {
        List<Device> list= userService.getUserDevices(-1);
        Assert.isTrue(list.size() == 0);
    }
}
