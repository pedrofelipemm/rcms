package br.ufscar.rcms.model.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Entity implements Serializable {

    protected static final int COLUMN_DEFAULT_LENGTH = 5000;

    public abstract Long getId();
}