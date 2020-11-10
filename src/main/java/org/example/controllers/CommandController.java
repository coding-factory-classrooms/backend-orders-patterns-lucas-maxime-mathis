package org.example.controllers;

import org.example.core.Template;
import org.example.models.CommandSystem;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class CommandController {
    private final CommandSystem commandSystem;

    public CommandController(CommandSystem commandSystem) {
        this.commandSystem = commandSystem;
    }

    public String detail(Request request, Response response){
        return Template.render("command.html", new HashMap<>());
    }
}
