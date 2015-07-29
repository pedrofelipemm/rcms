package br.ufscar.rcms.scorecard.rest;

import java.util.List;

public final class Wrapper<T> {

    private final Integer size;
    private final Integer groupSize;
    // private Integer currentPage;
    // private Integer pageSize;
    private final List<T> data;

    public Wrapper(final Integer groupSize, final Integer size, final List<T> data) {
        this.groupSize = groupSize;
        this.size = size;
        this.data = data;
    }

    public Integer getGroupSize() {
        return groupSize;
    }

    public Integer getSize() {
        return size;
    }

    public List<T> getData() {
        return data;
    }
}