package org.example.controllers;

import org.example.TimeMachine;
import org.example.core.Template;
import org.example.models.OrderSystem;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class DashboardController {
    private final OrderSystem orderSystem;
    private final TimeMachine timeMachine;

    public DashboardController(OrderSystem orderSystem, TimeMachine timeMachine) {
        this.orderSystem = orderSystem;
        this.timeMachine = timeMachine;
    }

    public String detail(Request request, Response response){
        String action = request.queryParamOrDefault("action","");

        if(action.equals("undo")){
            timeMachine.undo();
        }else if(action.equals("redo")){
            timeMachine.redo();
        }

        Map<String,Object> model = new HashMap<>();
        model.put("orders", orderSystem.getOrders());
        model.put("history", timeMachine.getSnapshots());
        model.put("historyCurrentId", timeMachine.getIndex() + 1);
        return Template.render("dashboard.html", model);
    }
}
