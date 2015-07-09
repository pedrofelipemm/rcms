package br.ufscar;

public class Sample {

    private int id;
    private String description;

    public Sample() {/* Serialization */}

    public Sample(final int id, final String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    
}