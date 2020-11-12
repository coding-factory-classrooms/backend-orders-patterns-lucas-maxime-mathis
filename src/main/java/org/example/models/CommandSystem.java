package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class CommandSystem {

    // ATTRIBUTES
    private List<Command> commands = new ArrayList<>();

    // GETTER
    public List<Command> getCommands() {
        return commands;
    }
    public Optional<Command> getCommandById(int id) {
        return commands.stream()
                .filter(command -> command.getId() == id)
                .findFirst();
    }

    // SETTER
    public void addCommand(Command command) {
        if(command.getState() != Command.State.NEW){
            return;
        }
        command.setId(getNextId());
        this.commands.add(command);
    }

    private int getNextId(){
        return commands.size() + 1;
    }
}
