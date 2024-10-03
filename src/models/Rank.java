package models;

public enum Rank {
    BRONZE(0,499,"bronze"),
    SILVER(500,999,"silver"),
    GOLD(1000,1499,"gold"),
    PLATINUM(1500,1999,"platinum"),
    DIAMOND(2000,Integer.MAX_VALUE,"diamond");

    private final int lowerBound;
    private final int upperBound;
    private final String name;

    Rank(int lowerBound, int upperBound, String name) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.name = name;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public String getName() {
        return name;
    }
}
