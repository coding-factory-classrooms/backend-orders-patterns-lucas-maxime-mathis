package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class CommandSystem {

    // ATTRIBUTES
    private List<Command> commands = new ArrayList<>();

    // GETTER
    public List<Command> getCommands() {
        return commands;
    }

    // SETTER
    public void addCommand(Command command) {
        if(command.getState() != Command.State.NEW){
            return;
        }

        this.commands.add(command);
    }
}
