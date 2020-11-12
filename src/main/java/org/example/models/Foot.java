package org.example.models;

public class Foot extends Organ {
    public enum Type{
        LEFT,
        RIGHT
    }

    // ATTRIBUTES
    private float size = 0;
    private Type type = Type.LEFT;

    // GETTER
    @Override
    public String getDescription() { return "Size = " + size; }
    @Override
    public String getName() { return "Foot - " + type.name(); }
    @Override
    public Category getCategory() { return Category.BODY_PART; }
}
