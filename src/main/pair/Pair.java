package main.pair;

public class Pair {
    /**
     * Generic Pair class used in FractionImpl constructors
     */

    private int first;
    private int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int first() {
        return first;
    }

    public int second() {
        return second;
    }
}
