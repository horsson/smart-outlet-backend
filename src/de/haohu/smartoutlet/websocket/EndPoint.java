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
@ServerEndpoint("/endpoint/{clientId}")
public class EndPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(EndPoint.class);
	
    @OnOpen
    public void onOpen(@PathParam("clientId") String clientId, Session session){

        LOGGER.debug("CleintId is " + clientId);

        session.addMessageHandler(new MessageHandler.Whole<String>(){
            @Override
            public void onMessage(String message) {
                System.out.println("Received message: "+message);
            }
        });

        if (clientId != null){
            Device device = new Device();
            device.setId(clientId);
            device.setSession(session);
            device.setCreationTime(new Date());
            DeviceManager.getInstance().addDevice(device);
        }
    }

    @OnClose
    public void onClose(Session session){
        LOGGER.debug("Session %s is closed.", session.getId());
        //Remove the devices from the list.
        DeviceManager.getInstance().removeDeviceBySessionId(session.getId());
    }


    @OnMessage
    public void onMessage(String msg, Session session){

    }
    
    @OnError
    public void onError(Throwable error, Session session){
    	LOGGER.error("Error occurs. The reason is "+ error.getMessage());
    }
    
    
}
