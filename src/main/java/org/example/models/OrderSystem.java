package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandSystem {

    // ATTRIBUTES
    private List<Command> commands = new ArrayList<>();
    private final LogSystem log;

    public CommandSystem(LogSystem log) {
        this.log = log;
    }

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
    public boolean addCommand(Command command) {
        if(command.getState() != Command.State.NEW){
            return false;
        }
        command.setId(getNextId());
        this.commands.add(command);
        String message = "Added new order id : "+ command.getId();
        log.addLog(message);
        return true;
    }

    private int getNextId(){ return commands.size() + 1; }

    public OrderSystemSnapshot createSnapshot(){
        return new OrderSystemSnapshot(commands);
    }

    public void restore(OrderSystemSnapshot snapshot){
        commands = snapshot.getCommands();
    }
}
