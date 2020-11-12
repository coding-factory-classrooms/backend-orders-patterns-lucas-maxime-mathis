package org.example;

import org.example.models.OrderSystem;
import org.example.models.OrderSystemSnapshot;

import java.util.ArrayList;
import java.util.List;

public class TimeMachine {
    private List<OrderSystemSnapshot> snapshots = new ArrayList<>();
    private final OrderSystem orderSystem;
    private int index = -1;

    public TimeMachine(OrderSystem orderSystem) {
        this.orderSystem = orderSystem;
    }

    public boolean undo(){
        if(index <= 0){
            return false;
        }
        snapshots.forEach(snapshot -> {
            System.out.println(snapshot + "size : "+snapshot.getOrders().size());
        });
        orderSystem.restore(snapshots.get(--index));
        return true;
    }

    public void makeBackup(){
        OrderSystemSnapshot snapshot = orderSystem.createSnapshot();
        System.out.println(snapshot.getOrders().size());
        snapshots.add(snapshot);
        index++;
    }

    public List<OrderSystemSnapshot> getSnapshots() {
        return snapshots;
    }

    public int getIndex() {
        return index;
    }
}
