package de.haohu.smartoutlet.model;

import java.util.Map;

/**
 * This is a message which is sent from Server to Device.
 */
public class Message {
    //Message id to idenitfy message
    private String id;
    private String cmd;
    private String val;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
