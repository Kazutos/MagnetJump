package model;

import static model.GameEngine.NEXT_PLAYER_ID;
import static model.UnitType.PLAYER;

public class Player {
    private int id;
    private Unit unit;
    private final String colour;


    public Player(int x, int y, String colour) {
        this.id = NEXT_PLAYER_ID++;
        this.unit = new Unit(x, y, new int[]{1, 1, 1, 1}, PLAYER);
        this.colour = colour;
    }

    public Player(Unit unit, String colour) {
        this.unit = unit;
        this.colour = colour;
    }

    public int x() {
        return unit.x();
    }

    public int y() {
        return unit.y();
    }

    public void up() {
        unit.setY(unit.y() + 1);
    }

    public void down() {
        unit.setY(unit.y() - 1);
    }

    public void right() {
        unit.setX(unit.x() + 1);
    }

    public void left() {
        unit.setX(unit.x() - 1);
    }

    public Unit getUnit() {
        return unit;
    }

    public int getId() {
        return id;
    }

    public String getColour() {
        return colour;
    }


}
