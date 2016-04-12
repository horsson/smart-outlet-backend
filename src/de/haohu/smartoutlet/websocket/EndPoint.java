package de.haohu.smartoutlet.websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import de.haohu.smartoutlet.devices.Device;
import de.haohu.smartoutlet.manager.DeviceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

/**
 * One endpoint connects to one peer.
 * It should be called with following URL:
 * wss://host/endpoint/de0-eadsf-defads-dde
 */
@ServerEndpoint("/endpoint/{deviceId}")
public class EndPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(EndPoint.class);

    private Device device;
	
    @OnOpen
    public void onOpen(@PathParam("deviceId") String deviceId, Session session){

        LOGGER.debug("DeviceId is {}", deviceId);
        LOGGER.debug("Session ID is {}", session.getId());
        LOGGER.debug("The instance of EndPoint is "+ this);

        if (deviceId != null){
            device = new Device();
            device.setId(deviceId);
            device.setSession(session);
            device.setCreationTime(new Date());
            DeviceManager.getInstance().addDevice(device);
        }
    }

    @OnClose
    public void onClose(Session session){
        LOGGER.debug("Session {} is closed.", session.getId());
        //Remove the devices from the list.
        DeviceManager.getInstance().removeDeviceBySessionId(session.getId());
    }


    @OnMessage
    public void onMessage(String msg, Session session){
        LOGGER.debug("I got the message, {}", msg);
    }
    
    @OnError
    public void onError(Throwable error, Session session){
    	LOGGER.error("Error occurs. The reason is "+ error.getMessage(), error);
    }
    
    
}
