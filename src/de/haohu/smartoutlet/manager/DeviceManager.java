package de.haohu.smartoutlet.manager;

import de.haohu.smartoutlet.devices.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by d058856 on 03/04/16.
 */
public class DeviceManager {
    //TODO: Device manager should also create a timer to check the availability of Device

    private static DeviceManager ourInstance = new DeviceManager();

    private ConcurrentMap<String, Device> devices = new ConcurrentHashMap<>();


    public static DeviceManager getInstance() {
        return ourInstance;
    }

    private DeviceManager() {
    }

    public void addDevice(Device device){
        devices.put(device.getId(), device);
    }


    public Device getDevice(String clientId){
        return devices.get(clientId);
    }

    public List<Device> getDevices(){
        return  new ArrayList<>(devices.values());
    }

    public void removeDeviceBySessionId(String sessionId){

        for (String deviceId: devices.keySet()) {
            Device device = devices.get(deviceId);
            if (device.getSession().getId().equals(sessionId)){
                devices.remove(deviceId, device);
            }
        }
    }


}
