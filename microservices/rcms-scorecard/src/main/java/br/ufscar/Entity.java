package br.ufscar;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Entity implements Serializable {

    public abstract Long getId();
}