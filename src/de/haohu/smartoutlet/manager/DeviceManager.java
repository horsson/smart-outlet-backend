package de.haohu.smartoutlet.manager;

import de.haohu.smartoutlet.annotations.CleanResource;
import de.haohu.smartoutlet.devices.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@CleanResource
public class DeviceManager implements Cleanable {
    //TODO: Device manager should also create a timer to check the availability of Device

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceManager.class);
    private static DeviceManager ourInstance = new DeviceManager();

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private ConcurrentMap<String, Device> devices = new ConcurrentHashMap<>();


    public static DeviceManager getInstance() {
        return ourInstance;
    }

    private DeviceManager() {
        executorService.scheduleAtFixedRate(new DeviceCheckTask(), 10, 10, TimeUnit.SECONDS);
    }

    public void addDevice(Device device){
        devices.put(device.getId(), device);
    }



    public Device getDevice(String deviceId){
        return devices.get(deviceId);
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

    @Override
    public void cleanup() {
        if (!executorService.isShutdown()){
             executorService.shutdown();
        }
    }

    //Class is used to check Device.
    class DeviceCheckTask implements Runnable{
        @Override
        public void run() {
            if (devices.size() == 0){
                LOGGER.debug("No device, return.");
                return;
            }
        }
    }


}
