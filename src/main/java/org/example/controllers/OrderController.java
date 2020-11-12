package org.example.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.core.Template;
import org.example.models.*;
import spark.Request;
import spark.Response;

import java.util.*;

import static java.lang.Integer.parseInt;

public class OrderController {
    private final OrderSystem orderSystem;

    public OrderController(OrderSystem orderSystem) {
        this.orderSystem = orderSystem;
    }

    public String detail(Request request, Response response){
        int id = Integer.parseInt(request.params("id"));
        Map<String, Object> model = new HashMap<>();

        Optional<Order> optionalOrder = orderSystem.getOrderById(id);

        if(optionalOrder.isEmpty()){
            response.redirect("/");
        }else{
            Order order = optionalOrder.get();
            model.put("order", order);
        }

        return Template.render("order_team.html", model);
    }

    public String changeOrderState(Request request, Response response) {

        int index = request.body().indexOf("=") + 1;
        String newState = request.body().substring(index);
        int orderId = Integer.parseInt(request.params("id"));
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

        Optional<Order> optionalOrder = orderSystem.getOrderById(orderId);

        if(optionalOrder.isEmpty()){
            response.redirect("/");
        }else{
            Order order = optionalOrder.get();
            order.setState(state);

            model.put("order", order);
            model.put("id", orderId);
        }

        return Template.render("order_team.html", model);
    }

    public String infoOrder(Request request, Response response){
        Map<String,Object> model = new HashMap<>();
        int id = parseInt(request.params("id"));

        Optional<Order> order = orderSystem.getOrderById(id);
        order.ifPresentOrElse(
                value -> model.put("order", value),
                () -> response.redirect("/")
        );

        return Template.render("order_customer.html", model);
    }

    public String addOrder(Request request, Response response){
        return Template.render("new_order.html", new HashMap<>());
    }

    public String placeOrder(Request request, Response response) {
        String json = request.body();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonArray arr = jsonObject.getAsJsonArray("organs");

        List<String> organs = new ArrayList<>();
        List<String> conditions = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            String organ = arr.get(i).getAsJsonObject().get("organ").getAsString();
            String condition = arr.get(i).getAsJsonObject().get("condition").getAsString();
            organs.add(organ);
            conditions.add(condition);
        }
        System.out.println("Tableau organs :" + organs);
        System.out.println("Tableau conditions :" + conditions);


        Order order = new Order();

        for (int i = 0; i < organs.size(); i++){
            String organStr = organs.get(i);

            if (organStr.equals("Foot")){
                Foot foot = new Foot();
                foot.setCondition(Organ.Condition.valueOf(conditions.get(i)));
                order.addOrgan(foot);
            }else if(organStr.equals("Lung")){
                Lung lung = new Lung();
                lung.setCondition(Organ.Condition.valueOf(conditions.get(i)));
                order.addOrgan(lung);
            }
        }

        orderSystem.addOrder(order);

        System.out.println("LA COMMANDE :"+order);

        String idRedirect = Integer.toString((orderSystem.getOrders().size()));

        System.out.println("String redirect :/orders/"+idRedirect+"/info");

        return idRedirect;
    }
}
