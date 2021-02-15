package org.utils;


public class Pair<T, X> {
    private final T first;
    private final X second;

    public void roastKarel() {
        System.out.println("Kerle naser si");
    }


    public Pair(T first, X second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return this.first;
    }

    public X second() {
        return this.second;
    }

}
