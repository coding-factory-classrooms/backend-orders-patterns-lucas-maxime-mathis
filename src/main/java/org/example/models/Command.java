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
    private LocalDate createTime = LocalDate.now();
    private List<Organ> organs = new ArrayList<>();

    // GETTER
    public State getState() { return state; }
    public LocalDate getCreateTime() { return createTime; }
    public List<Organ> getOrgans() { return organs; }

    // SETTER
    public void setOrgans(List<Organ> organs) { this.organs = organs; }
    public void setState(State state) { this.state = state; }

}
