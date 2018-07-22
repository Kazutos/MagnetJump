package util;

import model.Movement;

public class Command {
    private int playerId;
    private model.Movement movement;

    public Command(int playerId, Movement movement) {
        this.playerId = playerId;
        this.movement = movement;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }
}
