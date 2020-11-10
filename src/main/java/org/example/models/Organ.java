package org.example.models;

public abstract class Organ {
    public enum Condition{
        BRAND_NEW,
        GOOD,
        CORRECT,
        UNHEALTHY,
    }
    public enum Category{
        INTERN,
        BODY_PART
    }

    // ATTRIBUTES
    private Condition condition = Condition.BRAND_NEW;
    private float price = 0;

    // ABSTRACT
    public abstract String getDescription();
    public abstract String getName();
    public abstract Category getCategory();

    // GETTER
    public Condition getCondition() { return condition; }
    public float getPrice() { return price; }

    // SETTER
    public void setPrice(float price) { this.price = price; }
    public void setCondition(Condition condition) { this.condition = condition; }
}
