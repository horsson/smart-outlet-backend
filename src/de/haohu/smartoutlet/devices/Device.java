package de.haohu.smartoutlet.devices;

import javax.websocket.Session;
import java.util.Date;

/**
 * Created by d058856 on 03/04/16.
 */
public class Device {

    private String id;
    private Date creationTime;

    private transient Session session;

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
