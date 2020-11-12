package org.example;

import org.example.models.Order;
import org.example.models.OrderSystem;
import org.example.models.OrderSystemSnapshot;

import java.util.ArrayList;
import java.util.List;

public class TimeMachine implements OrderSystem.OnSystemOrderChange {
    private List<OrderSystemSnapshot> snapshots = new ArrayList<>();
    private final OrderSystem orderSystem;
    private int index = -1;

    public TimeMachine(OrderSystem orderSystem) {
        this.orderSystem = orderSystem;
        this.orderSystem.addOnOnSystemOrderChangeListener(this);
    }

    public boolean undo(){
        if(index <= 0){
            return false;
        }
        index -= 1;
        orderSystem.restore(snapshots.get(index));
        return true;
    }

    public boolean redo(){
        if(index + 1 >= snapshots.size()){
            return false;
        }
        index += 1;
        orderSystem.restore(snapshots.get(index));
        return true;
    }

    public void makeBackup(){
        OrderSystemSnapshot snapshot = orderSystem.createSnapshot();
        snapshots.add(snapshot);
        index++;
    }

    public List<OrderSystemSnapshot> getSnapshots() {
        return snapshots;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void onAddOrder(Order order) {
        makeBackup();
    }

    @Override
    public void onOrderChange(Order order) {
        makeBackup();
    }
}
