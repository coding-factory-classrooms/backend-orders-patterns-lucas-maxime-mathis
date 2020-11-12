package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderSystem implements Order.OnOrderStateChange {
    public interface OnSystemOrderChange{
        void onAddOrder(Order order);
        void onOrderChange(Order order);
    }

    // ATTRIBUTES
    private List<Order> orders = new ArrayList<>();
    private final LogSystem log;
    private OnSystemOrderChange onSystemOrderChange;

    public OrderSystem(LogSystem log) {
        this.log = log;
    }

    // GETTER
    public List<Order> getOrders() {
        return orders;
    }
    public Optional<Order> getOrderById(int id) {
        return orders.stream()
                .filter(command -> command.getId() == id)
                .findFirst();
    }

    // SETTER
    public boolean addOrder(Order order) {
        if(order.getState() != Order.State.NEW){
            return false;
        }
        order.setId(getNextId());
        order.addOnOrderStateChangeListener(this);
        this.orders.add(order);
        String message = "Added new order id : "+ order.getId();
        log.addLog(message);
        if(onSystemOrderChange != null){
            onSystemOrderChange.onAddOrder(order);
        }
        return true;
    }

    private int getNextId(){ return orders.size() + 1; }

    public void addOnOnSystemOrderChangeListener(OnSystemOrderChange listener){
        onSystemOrderChange = listener;
    }

    // SAVE SYSTEM
    public OrderSystemSnapshot createSnapshot(){
        String message = "Create Snapshot";
        log.addLog(message);
        return new OrderSystemSnapshot(orders);
    }
    public void restore(OrderSystemSnapshot snapshot){
        orders = snapshot.getOrders();
        orders.forEach(order -> order.addOnOrderStateChangeListener(this));
        String message = "Restore";
        log.addLog(message);
    }

    @Override
    public void onOrderStateChange(Order order) {
        log.addLog("order " + order.getId() + "state change to " + order.getState() );
        if(onSystemOrderChange != null){
            onSystemOrderChange.onOrderChange(order);
        }
    }
}
