package org.example.models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Command {
    public enum State{
        NEW,
        IN_PROGRESS,
        FINISHED,
        CANCELED
    }

    // ATTRIBUTES
    private State state = State.NEW;
    private int id = 0;
    private LocalDate createTime = LocalDate.now();
    private List<Organ> organs = new ArrayList<>();


    // GETTER
    public State getState() { return state; }
    public LocalDate getCreateTime() { return createTime; }
    public List<Organ> getOrgans() { return organs; }
    public int getId() { return id; }


    // SETTER
    public void addOrgan(Organ organ) { this.organs.add(organ); }
    public void setState(State state) { this.state = state; }
    public void setId(int id) { this.id = id; }

}
