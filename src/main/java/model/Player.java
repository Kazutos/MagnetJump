package model;

import java.util.UUID;

public class Player {
    private String id;
    private Coordinates coordinates;
    private final String colour;


    public Player(int x, int y, String colour) {
        this.id = UUID.randomUUID().toString();
        this.coordinates = new Coordinates(x, y);
        this.colour = colour;
    }

    public Player(Coordinates coordinates, String colour) {
        this.coordinates = coordinates;
        this.colour = colour;
    }

    public int x() {
        return coordinates.x();
    }

    public void up(int upMost) {
        if (coordinates.y() < upMost) {
            coordinates.setY(coordinates.y() + 1);
        }
    }

    public void down(int downMost) {
        if (coordinates.y() > downMost) {
            coordinates.setY(coordinates.y() - 1);
        }
    }

    public void right(int rightMost) {
        if (coordinates.x() < rightMost) {
            coordinates.setX(coordinates.x() + 1);
        }
    }

    public void left(int leftMost) {
        if (coordinates.x() > leftMost) {
            coordinates.setX(coordinates.x() - 1);
        }
    }

    //TODO restrict?
    public void setX(int x) {
        coordinates.setX(x);
    }

    public int y() {
        return coordinates.y();
    }

    // TODO restrict?
    public void setY(int y) {
        coordinates.setY(y);
    }

    public String getId() {
        return id;
    }

    public String getColour() {
        return colour;
    }


}
