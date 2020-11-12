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
    public Optional<Order> getCommandById(int id) {
        return orders.stream()
                .filter(command -> command.getId() == id)
                .findFirst();
    }

    // SETTER
    public boolean addCommand(Order command) {
        if(command.getState() != Order.State.NEW){
            return false;
        }
        command.setId(getNextId());
        this.orders.add(command);
        String message = "Added new order id : "+ command.getId();
        log.addLog(message);
        return true;
    }

    private int getNextId(){ return orders.size() + 1; }

    // SAVE SYSTEM
    public OrderSystemSnapshot createSnapshot(){
        String message = "Create Snapshot";
        log.addLog(message);
        return new OrderSystemSnapshot(orders);
    }
    public void restore(OrderSystemSnapshot snapshot){
        orders = snapshot.getOrders();
        String message = "Restore";
        log.addLog(message);
    }
}
