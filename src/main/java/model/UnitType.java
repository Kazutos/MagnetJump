package model;

public enum UnitType {
    SPACE(0), BAR(1), PLAYER(2), TARGET(3);

    private int type;

    UnitType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return Integer.toString(type);
    }
}
