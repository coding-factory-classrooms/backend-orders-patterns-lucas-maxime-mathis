package org.example.models;

import java.util.List;
import java.util.stream.Collectors;

public class OrderSystemSnapshot {
    private List<OrderSnapshot> orders;

    public OrderSystemSnapshot(List<Order> orders){
        this.orders = orders.stream()
                .map(Order::createSnapshot)
                .collect(Collectors.toList());
    }

    public List<Order> getOrders() {
        return orders.stream().map(orderSnapshot -> {
            Order order = new Order();
            order.restore(orderSnapshot);
            return order;
        }).collect(Collectors.toList());
    }
}