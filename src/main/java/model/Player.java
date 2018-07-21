package model;

import java.util.UUID;

public class Player {
    private String id;
    private Unit unit;
    private final String colour;


    public Player(int x, int y, String colour) {
        this.id = UUID.randomUUID().toString();
        this.unit = new Unit(x, y);
        this.unit.setMagnets(new int[]{1, 1, 1, 1});
        this.colour = colour;
    }

    public Player(Unit unit, String colour) {
        this.unit = unit;
        this.colour = colour;
    }

    public int x() {
        return unit.x();
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

    //TODO restrict?
    public void setX(int x) {
        unit.setX(x);
    }

    public int y() {
        return unit.y();
    }

    // TODO restrict?
    public void setY(int y) {
        unit.setY(y);
    }

    public String getId() {
        return id;
    }

    public String getColour() {
        return colour;
    }


}
