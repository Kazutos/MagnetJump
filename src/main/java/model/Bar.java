package model;

import static model.BarPosition.VERTICAL;

public class Bar {
    private Coordinates initial;
    private int size;
    private BarPosition position;
    private Coordinates[] units;

    public Bar(int initX, int initY, int size, BarPosition position) {
        if (size < 1) {
            throw new IllegalArgumentException("A bar must have at least one unit!");
        }
        this.initial = new Coordinates(initX, initY);
        this.size = size;
        this.position = position;

        initializeUnits();
    }

    private void initializeUnits() {
        this.units = new Coordinates[size];
        units[0] = initial;

        if (VERTICAL.equals(position)) {
            for (int i = 1; i < size; i++) {
                units[i] = new Coordinates(initial.x(), initial.y() + i);
            }
        } else {
            for (int i = 1; i < size; i++) {
                units[i] = new Coordinates(initial.x() + i, initial.y());
            }
        }
    }

    public Coordinates[] getUnits() {
        return units;
    }
}
