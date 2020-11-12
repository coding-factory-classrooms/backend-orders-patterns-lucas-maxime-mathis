package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class OrderSystemSnapshot {
    private List<Order> orders;

    public OrderSystemSnapshot(List<Order> orders){
        this.orders = new ArrayList<>(orders);
    }

    public List<Order> getOrders() {
        return orders;
    }
}