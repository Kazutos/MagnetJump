package util;

import interfaces.Field;
import interfaces.PlayerControl;
import interfaces.State;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import ui.GameFieldController;
import model.Movement;

public class GameFieldState implements Field, PlayerControl, State {
    // Attribute
    private GameFieldController gameFieldController;


    // Constructor

    public GameFieldState(GameFieldController gameFieldController) {
        this.gameFieldController = gameFieldController;
    }


    // Setter




    // Method

    @Override
    public void setFieldWidth(int width) {
        gameFieldController.setFieldWidth(width);
    }

    @Override
    public void setFieldHeight(int height) {
        gameFieldController.setFieldHeight(height);
    }

    @Override
    public void setStone(int x, int y, boolean magnetOnTop, boolean magnetOnBottom, boolean magnetOnRight, boolean magnetOnLeft) {
        gameFieldController.addStone(x,y, magnetOnTop, magnetOnBottom, magnetOnRight, magnetOnLeft);
    }

    @Override
    public void setPlayer(int x, int y, Color color) {
        gameFieldController.addPlayer(x, y, color);
    }

    @Override
    public void setCoin(int x, int y) {
        gameFieldController.addCoin(x, y);
    }

    @Override
    public void move(int playerId, Movement movements) {
        gameFieldController.move(playerId, movements);
    }

    @Override
    public void createField() {
        gameFieldController.createField();
    }

    @Override
    public void startPhase() {

    }

    @Override
    public void endPhase() {
        gameFieldController.startAnimation();
    }



    // Just for demo
    public SimpleObjectProperty<KeyEvent> getKeyEvent(){
        return gameFieldController.kep;
    }
}
