package org.example.controllers;

import org.example.core.Template;
import org.example.models.Command;
import org.example.models.CommandSystem;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class CommandController {
    private final CommandSystem commandSystem;

    public CommandController(CommandSystem commandSystem) {
        this.commandSystem = commandSystem;
    }

    public String detail(Request request, Response response){
        return Template.render("command.html", new HashMap<>());
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
