package com.affinitas.chat.users.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by mohammad on 2/25/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DevicesRepositoryTests {
    @Autowired
    DevicesRepository devicesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Test
    public void testAddDevice() {
        User user1 = usersRepository.getUserById(1);
        Device device = new MockDevice();
        device = devicesRepository.addUserDevice(device, new User());
        int id = device.getDeviceId();
        devicesRepository.addUserDevice(new MockDevice(), user1);
        Device someDevice = devicesRepository.addUserDevice(new MockDevice(), user1);
        Device lastDevice = devicesRepository.addUserDevice(new MockDevice(), user1);
        Assert.isTrue(devicesRepository.getDeviceById(id).getDeviceId() == id);
        Assert.isTrue(devicesRepository.getDeviceById(lastDevice.getDeviceId()).getDeviceId() == lastDevice.getDeviceId());
        Assert.isTrue(devicesRepository.getDeviceById(someDevice.getDeviceId()).getDeviceId() == someDevice.getDeviceId());
    }

    @Test
    public void testGetUserDevices() {
        User user1 = usersRepository.addUser(new User());
        Device device = new MockDevice();
        device = devicesRepository.addUserDevice(device, user1);
        List<Device> list = devicesRepository.getUserDevices(user1);
        Assert.isTrue(list.size() == 1);

        devicesRepository.addUserDevice(new MockDevice(), user1);
        devicesRepository.addUserDevice(new MockDevice(), user1);
        Assert.isTrue(list.size() == 3);
    }

    @Test
    public void testNoUserDevices() {
        List<Device> list = devicesRepository.getUserDevices(new User());
        Assert.isTrue(list.size() == 0);
    }

    @Test
    public void testAddNullUserDevices() {
        List<Device> list = devicesRepository.getUserDevices(null);
        Assert.isTrue(list.size() == 0);
    }
}
