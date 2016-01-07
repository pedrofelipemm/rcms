package br.ufscar.rcms.util;

import org.springframework.stereotype.Component;

@Component
public class ForkJoinPool {

    private java.util.concurrent.ForkJoinPool pool = new java.util.concurrent.ForkJoinPool();

    public java.util.concurrent.ForkJoinPool getPool() {
        return pool;
    }

}