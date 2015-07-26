package br.ufscar.rcms.scorecard;

import java.io.Serializable;

public class Sample implements Serializable {

    private static final long serialVersionUID = -2522173199005092944L;

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