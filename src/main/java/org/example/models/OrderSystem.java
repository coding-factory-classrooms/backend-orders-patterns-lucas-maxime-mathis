package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderSystem {

    // ATTRIBUTES
    private List<Order> orders = new ArrayList<>();
    private final LogSystem log;


    public OrderSystem(LogSystem log) {
        this.log = log;
    }

    // GETTER
    public List<Order> getOrders() {
        return orders;
    }
    public Optional<Order> getOrderById(int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst();
    }

    // SETTER
    public boolean addOrder(Order order) {
        if(order.getState() != Order.State.NEW){
            return false;
        }
        order.setId(getNextId());
        this.orders.add(order);
        String message = "Added new order id : "+ order.getId();
        log.addLog(message);
        return true;
    }

    private int getNextId(){ return orders.size() + 1; }

    public OrderSystemSnapshot createSnapshot(){
        return new OrderSystemSnapshot(orders);
    }

    public void restore(OrderSystemSnapshot snapshot){
        orders = snapshot.getOrders();
    }
}
