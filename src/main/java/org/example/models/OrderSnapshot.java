package org.example.models;

import java.time.LocalDateTime;
import java.util.List;

public class OrderSnapshot {
    private final List<Organ> organs;
    private final Order.State state;
    private final LocalDateTime createTime;
    private final LocalDateTime lastModification;
    private final int id;

    public OrderSnapshot(List<Organ> organs, Order.State state, LocalDateTime createTime, LocalDateTime lastModification, int id) {
        this.organs = organs;
        this.state = state;
        this.createTime = createTime;
        this.lastModification = lastModification;
        this.id = id;
    }

    public List<Organ> getOrgans() {
        return organs;
    }

    public Order.State getState() {
        return state;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getLastModification() {
        return lastModification;
    }

    public int getId() {
        return id;
    }
}
