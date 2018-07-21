package model;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinates)) {
            return false;
        }

        Coordinates other = (Coordinates) obj;
        return (this.x == other.x && this.y == other.y);
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
