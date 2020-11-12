package org.example.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    public enum State{
        NEW,
        IN_PROGRESS,
        FINISHED,
        CANCELED
    }

    // ATTRIBUTES
    private State state = State.NEW;
    private int id = 0;
    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime lastModification = createTime;
    private List<Organ> organs = new ArrayList<>();


    // GETTER
    public State getState() { return state; }
    public LocalDateTime getCreateTime() { return createTime; }
    public List<Organ> getOrgans() { return organs; }
    public int getId() { return id; }
    public LocalDateTime getLastModification() { return lastModification; }

    // SETTER
    public void addOrgan(Organ organ) {
        lastModification = LocalDateTime.now();
        this.organs.add(organ);
    }
    public void setState(State state) {
        lastModification = LocalDateTime.now();
        this.state = state;
    }
    public void setId(int id) {
        lastModification = LocalDateTime.now();
        this.id = id;
    }

    // SAVE SYSTEM
    public OrderSnapshot createSnapshot(){
        return new OrderSnapshot(organs,state,createTime,lastModification,id);
    }
    public void restore(OrderSnapshot snapshot){
        id = snapshot.getId();
        organs = snapshot.getOrgans();
        createTime = snapshot.getCreateTime();
        lastModification = snapshot.getLastModification();
        state = snapshot.getState();
    }

}
