package org.example.controllers;

import org.example.core.Template;
import org.example.models.Command;
import org.example.models.CommandSystem;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class CommandController {
    private final CommandSystem commandSystem;

    public CommandController(CommandSystem commandSystem) {
        this.commandSystem = commandSystem;
    }

    public String detail(Request request, Response response){

        List<Command> commandList = commandSystem.getCommands();
        int index = Integer.parseInt(request.params("id")) - 1;
        Command command = commandList.get(index);

        Map<String, Object> model = new HashMap<>();
        model.put("command", command);
        model.put("id", index);

        return Template.render("info_order.html", model);
    }

    public String changeCommandState(Request request, Response response) {
        List<Command> commandList = commandSystem.getCommands();

        Integer index = request.body().indexOf("=") + 1;
        String newState = request.body().substring(index);
        Integer commandId = Integer.parseInt(request.params("id")) - 1;
        Command.State state = null;

        switch (newState) {
            case "new":
                state = Command.State.NEW;
                break;
            case "in_progress":
                state = Command.State.IN_PROGRESS;
                break;
            case "finished":
                state = Command.State.FINISHED;
                break;
            case "canceled":
                state = Command.State.CANCELED;
                break;
            default:
                break;
        }

        Command command = commandList.get(commandId);
        command.setState(state);

        Map<String, Object> model = new HashMap<>();
        model.put("command", command);
        model.put("id", commandId);
        return Template.render("info_order.html", model);
    }

    public String infoCommand(Request request, Response response){
        Map<String,Object> model = new HashMap<>();
        int id = parseInt(request.params("id"));

        Optional<Command> command = commandSystem.getCommandById(id);
        command.ifPresentOrElse(
                value -> model.put("command", value),
                () -> response.redirect("/")
        );

        return Template.render("command_info.html", model);
    }
}
