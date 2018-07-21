package model;

import static model.UnitType.SPACE;

public class Unit {
    private int x;
    private int y;

    // A 4-integer array representing the magnetic properties of the left, right, bottom and top edges ( in this exact order) of the unit.
    private int[] magnets;
    private UnitType type;

    public Unit(int x, int y, UnitType type) {
        this.x = x;
        this.y = y;
        this.magnets = new int[]{0, 0, 0, 0};
        this.type = SPACE;
    }

    public Unit(int x, int y, int[] magnets, UnitType type) {
        this.x = x;
        this.y = y;
        setMagnets(magnets);
        this.type = SPACE;
    }

    public int x() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int y() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[] getMagnets() {
        return magnets;
    }

    public void setMagnets(int[] magnets) {
        this.magnets = magnets;
    }

    public UnitType getType() {
        return type;
    }

    public void setType(UnitType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Unit)) {
            return false;
        }

        Unit other = (Unit) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return 73 * x * y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
