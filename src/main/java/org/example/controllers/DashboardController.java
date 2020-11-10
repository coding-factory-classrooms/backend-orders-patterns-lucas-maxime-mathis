package org.example.controllers;

import org.example.core.Template;
import org.example.models.CommandSystem;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class DashboardController {
    private final CommandSystem commandSystem;

    public DashboardController(CommandSystem commandSystem) {
        this.commandSystem = commandSystem;
    }

    public String detail(Request request, Response response){
        Map<String,Object> model = new HashMap<>();
        model.put("commands",commandSystem.getCommands());
        return Template.render("dashboard.html", model);
    }
}
