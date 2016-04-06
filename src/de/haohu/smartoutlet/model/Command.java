package de.haohu.smartoutlet.model;

/**
 * Created by d058856 on 29/03/16.
 */
public class Command {

    private String deviceId;
    private String cmd;
    private String value;


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
