package com.affinitas.chat.users.data;

import com.affinitas.chat.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * provide storage and management for Users
 */
@Component
public class DevicesRepository {
	private Map<Integer, Device> devices = new HashMap<>();
	//MAP<USERID, Device>
	private Map<Integer, List<Device>> userDevices = new HashMap<>();
	private int lastDeviceId = 0;

	@Autowired
	UsersRepository usersRepository;

	public Device getDeviceById(int deviceId) {
		return devices.get(deviceId);
	}
	public List<Device> getUserDevices(User user) {
		if(user == null) {
			return Collections.emptyList();
		}
		List<Device> list = userDevices.get(user.getId());
		if(list == null) {
			return Collections.emptyList();
		}

		return list;

	}

	public Device addUserDevice(Device device, User user) {
		lastDeviceId++;
		device.setDeviceId(lastDeviceId);
		String name = device.getDeviceName();
		if(name == null || name.isEmpty()) {
			device.setDeviceName(device.getClass().getName() + lastDeviceId);
		}
		devices.put(device.getDeviceId(), device);

		//if valid user link it with a device
		if(user != null) {
			User existUser = usersRepository.getUserById(user.getId());
			if(existUser != null) {
				Logger.LogInfo("user " + user.getId() + " linked with device " +device.getDeviceId());
				List<Device> list = userDevices.get(user.getId());
				if(list == null) {
					list = new ArrayList<>();
				}
				list.add(device);
				userDevices.put(user.getId(), list);
			}
		}

		return device;
	}


	@PostConstruct
	private void initRepository() {
		User user1 = usersRepository.getUserById(1);
		User user2 = usersRepository.getUserById(2);
		addUserDevice(new MockDevice(), user1);
		addUserDevice(new MockDevice(), user1);
		addUserDevice(new MockDevice(), user1);
		addUserDevice(new MockDevice(), user2);
		addUserDevice(new MockDevice(), user2);
	}
}
