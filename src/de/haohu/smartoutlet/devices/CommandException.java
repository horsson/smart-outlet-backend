package de.haohu.smartoutlet.devices;

import de.haohu.smartoutlet.model.Command;

/**
 * Created by d058856 on 07/04/16.
 */
public class CommandException extends Exception {


    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public CommandException(String message, Command command){
        super(message);
        this.command = command;
    }


    public CommandException(String message){
        this(message, null);
    }

    public CommandException(){
        super();
    }



}
