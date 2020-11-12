package org.example.controllers;

import org.example.core.Template;
import org.example.models.Order;
import org.example.models.OrderSystem;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class OrderController {
    private final OrderSystem orderSystem;

    public OrderController(OrderSystem commandSystem) {
        this.orderSystem = commandSystem;
    }

    public String detail(Request request, Response response){
        int id = Integer.parseInt(request.params("id"));
        Map<String, Object> model = new HashMap<>();

        Optional<Order> optionalCommand = orderSystem.getCommandById(id);

        if(optionalCommand.isEmpty()){
            response.redirect("/");
        }else{
            Order command = optionalCommand.get();
            model.put("command", command);
        }

        return Template.render("order_team.html", model);
    }

    public String changeCommandState(Request request, Response response) {

        int index = request.body().indexOf("=") + 1;
        String newState = request.body().substring(index);
        int commandId = Integer.parseInt(request.params("id"));
        Map<String, Object> model = new HashMap<>();
        Order.State state = null;

        switch (newState) {
            case "new":
                state = Order.State.NEW;
                break;
            case "in_progress":
                state = Order.State.IN_PROGRESS;
                break;
            case "finished":
                state = Order.State.FINISHED;
                break;
            case "canceled":
                state = Order.State.CANCELED;
                break;
            default:
                break;
        }

        Optional<Order> optionalCommand = orderSystem.getCommandById(commandId);

        if(optionalCommand.isEmpty()){
            response.redirect("/");
        }else{
            Order command = optionalCommand.get();
            command.setState(state);

            model.put("command", command);
            model.put("id", commandId);
        }

        return Template.render("order_team.html", model);
    }

    public String infoCommand(Request request, Response response){
        Map<String,Object> model = new HashMap<>();
        int id = parseInt(request.params("id"));

        Optional<Order> command = orderSystem.getCommandById(id);
        command.ifPresentOrElse(
                value -> model.put("command", value),
                () -> response.redirect("/")
        );

        return Template.render("order_customer.html", model);
    }
}
