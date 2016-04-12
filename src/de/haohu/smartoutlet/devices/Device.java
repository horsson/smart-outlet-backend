package de.haohu.smartoutlet.devices;

import com.google.gson.Gson;
import de.haohu.smartoutlet.model.Command;
import de.haohu.smartoutlet.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Date;

/**
 *  It indicates a Device connecting to the Web Server.
 */
public class Device {



    private static final Logger LOGGER = LoggerFactory.getLogger(Device.class);
    private String id;
    private Date creationTime;
    private transient Session session;

    public void sendCommand(Command command) throws CommandException {
        if (session != null){
            RemoteEndpoint.Basic remote = session.getBasicRemote();
            Message message = new Message();
            message.setCmd(command.getCmd());
            message.setVal(command.getValue());
            Gson gson = new Gson();
            String json = gson.toJson(message);
            LOGGER.debug("Message to be sent to device:");
            LOGGER.debug(json);

            try {
                remote.sendText(json);
            } catch (IOException e) {
                LOGGER.error("Cannot send out message", e);
                throw new CommandException("Cannot send out message. Reason is "+ e.getMessage());
            }
        } else {
            LOGGER.error("Session is null.");
            throw new CommandException("Session is null");
        }
    }


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
