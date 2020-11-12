package org.example.controllers;

import org.example.core.Template;
import org.example.models.OrderSystem;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class DashboardController {
    private final OrderSystem orderSystem;

    public DashboardController(OrderSystem commandSystem) {
        this.orderSystem = commandSystem;
    }

    public String detail(Request request, Response response){
        Map<String,Object> model = new HashMap<>();
        model.put("commands", orderSystem.getOrders());
        return Template.render("dashboard.html", model);
    }
}
