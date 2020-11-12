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
        int id = Integer.parseInt(request.params("id"));
        Map<String, Object> model = new HashMap<>();

        Optional<Command> optionalCommand = commandSystem.getCommandById(id);

        if(optionalCommand.isEmpty()){
            response.redirect("/");
        }else{
            Command command = optionalCommand.get();
            model.put("command", command);
        }

        return Template.render("order_team.html", model);
    }

    public String changeCommandState(Request request, Response response) {

        int index = request.body().indexOf("=") + 1;
        String newState = request.body().substring(index);
        int commandId = Integer.parseInt(request.params("id"));
        Map<String, Object> model = new HashMap<>();
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

        Optional<Command> optionalCommand = commandSystem.getCommandById(commandId);

        if(optionalCommand.isEmpty()){
            response.redirect("/");
        }else{
            Command command = optionalCommand.get();
            command.setState(state);

            model.put("command", command);
            model.put("id", commandId);
        }

        return Template.render("order_team.html", model);
    }

    public String infoCommand(Request request, Response response){
        Map<String,Object> model = new HashMap<>();
        int id = parseInt(request.params("id"));

        Optional<Command> command = commandSystem.getCommandById(id);
        command.ifPresentOrElse(
                value -> model.put("command", value),
                () -> response.redirect("/")
        );

        return Template.render("order_customer.html", model);
    }
}
