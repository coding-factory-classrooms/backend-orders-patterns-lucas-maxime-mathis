package org.example.controllers;

import org.example.core.Template;
import org.example.models.CommandSystem;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class DashboardController {
    private final CommandSystem commandSystem;

    public DashboardController(CommandSystem commandSystem) {
        this.commandSystem = commandSystem;
    }

    public String detail(Request request, Response response){
        return Template.render("home.html", new HashMap<>());
    }
}
