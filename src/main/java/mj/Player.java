package mj;

public class Player {
    private Coordinates coordinates;
    private final String colour;


    public Player(int x, int y, String colour) {
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

    public void up() {
        coordinates.setY(coordinates.y() + 1);
    }

    public void down() {
        coordinates.setY(coordinates.y() - 1);
    }

    public void right() {
        coordinates.setX(coordinates.x() + 1);
    }

    public void left() {
        coordinates.setX(coordinates.x() - 1);
    }

    public void setX(int x) {
        coordinates.setX(x);
    }

    public int y() {
        return coordinates.y();
    }

    public void setY(int y) {
        coordinates.setY(y);
    }

    public String getColour() {
        return colour;
    }


}
