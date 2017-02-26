package com.affinitas.chat.users.data;

import com.affinitas.chat.messages.data.Message;

/**
 * Device used for Push notification
 * @author mohammad
 */
public abstract class Device {
    private int deviceId;
    private String deviceName;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void showNotification(Message message) {
        System.out.println("------------------" + deviceName + "---------------------");
        System.out.println("Message "+message.getMessage() + " from: " + message.getFromUser().getNickName());
        System.out.println("---------------------------------------");
    }

    //TODO:removed temp
    /*
    public abstract void subscribe();
    public abstract void deleteSubscribe();
    */
}
