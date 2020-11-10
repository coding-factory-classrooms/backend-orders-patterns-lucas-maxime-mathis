package org.example.models;

public class Lung extends Organ {

    // GETTER
    @Override
    public String getDescription() { return ""; }
    @Override
    public String getName() { return "Lung"; }
    @Override
    public Category getCategory() { return Category.INTERN; }
}
