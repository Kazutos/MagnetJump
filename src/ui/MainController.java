package ui;

import demo.MovementDemo;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import server.Server;
import util.Command;
import util.GameFieldState;
import util.HelpConnection;

import java.io.IOException;

public class MainController {
    // Attribute
    private GameFieldState gameFieldState;
    private SimpleObjectProperty<KeyEvent> kep;

    // FXML-Attribute
    @FXML
    private AnchorPane startPane;
    @FXML
    private AnchorPane gamePane;

    // Constructor


    // Action methods
    public void play(ActionEvent event){
        // Just switch the pane
        goToPlaymode();
        // TODO:

        // GamePane
        gameFieldState = loadGameField();

        kep = new SimpleObjectProperty<>();
        MovementDemo demo = new MovementDemo(gameFieldState);
        // gamePane.getScene().setOnKeyPressed(event1 -> demo.onKeyPressed(event1));
        demo.start();
        HelpConnection.getInstance().setGameFieldState(gameFieldState);

        refresh();

    }

    public void playVsMode(ActionEvent event){
        // Just switch the pane
        goToPlaymode();
        // TODO:
    }

    public void refresh(){
        Command tmp = HelpConnection.getInstance().pullCommand();
        if(tmp != null){
            gameFieldState.move(tmp.getPlayerId(), tmp.getMovement());
            gameFieldState.endPhase();
        }
        Platform.runLater(() -> refresh());
    }




    // Help Method
    public GameFieldState loadGameField(){
        AnchorPane gameField = null;
        GameFieldState gameFieldState = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameField.fxml"));
            gameField = loader.load();
            GameFieldController gfc = ((GameFieldController) loader.getController());
            gfc.init();
            gameFieldState = new GameFieldState(gfc);

        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane.setTopAnchor(gameField, 0.0);
        AnchorPane.setTopAnchor(gameField, 0.0);
        AnchorPane.setTopAnchor(gameField, 0.0);
        AnchorPane.setTopAnchor(gameField, 0.0);
        gamePane.getChildren().add(gameField);
        return gameFieldState;
    }






    // Help Method
    private void goToPlaymode(){
        startPane.setVisible(false);
        gamePane.setVisible(true);
    }
}
