package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainController {
    // Attribute

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
    }

    public void playVsMode(ActionEvent event){
        // Just switch the pane
        goToPlaymode();
    }






    // Help Method
    private void goToPlaymode(){
        startPane.setVisible(false);
        gamePane.setVisible(true);
    }
}
