package br.ufscar.rcms.scorecard.rest;

import java.util.List;

public final class Wrapper<T> {

    private final int size;
    // private int currentPage;
    // private int pageSize;
    private final List<T> data;

    public Wrapper(final int size, final List<T> data) {
        this.size = size;
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public List<T> getData() {
        return data;
    }

}