package model;

import static model.BarPosition.VERTICAL;
import static model.UnitType.BAR;

public class Bar {
    private Unit initial;
    private int size;
    private BarPosition position;
    private Unit[] units;

    public Bar(int x, int y, int size, BarPosition position) {
        if (size < 1) {
            throw new IllegalArgumentException("A bar must have at least one unit!");
        }
        this.initial = new Unit(x, y, BAR);
        this.size = size;
        this.position = position;

        initializeUnits();
    }

    private void initializeUnits() {
        this.units = new Unit[size];
        units[0] = initial;

        if (VERTICAL.equals(position)) {
            for (int i = 1; i < size; i++) {
                units[i] = new Unit(initial.x(), initial.y() + i, BAR);
            }
        } else {
            for (int i = 1; i < size; i++) {
                units[i] = new Unit(initial.x() + i, initial.y(), BAR);
            }
        }
    }

    public Unit[] getUnits() {
        return units;
    }
}
