package ui;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.image.ImageView;

import util.Movement;
import java.util.LinkedList;

public class GameFieldController {
    // Attribute
    private Pane mainPane;
    private LinkedList<Node> playerList;


    private int screenWidth;
    private int screenHeight;
    private int fieldWidth;
    private int fieldHeight;


    // Animation
    private ParallelTransition currentTransition;

    // Demo
    public SimpleObjectProperty<KeyEvent> kep;


    // FXML-Attribute
    @FXML
    private VBox gameStack;

    // Constructor
    public GameFieldController(){
        playerList = new LinkedList<>();
        currentTransition = new ParallelTransition();
        kep = new SimpleObjectProperty<>();

    }

    // Setter
    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setFieldWidth(int fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    public void setFieldHeight(int fieldHeight) {
        this.fieldHeight = fieldHeight;
    }

    // Init
    public void init(){
        int size = 800;
        mainPane = new Pane();
        mainPane.setStyle("-fx-background-color: white;");
        mainPane.maxHeight(size);
        mainPane.maxWidth(size);
        setScreenWidth(size);
        setScreenHeight(size);
        mainPane.setLayoutX(100);
        mainPane.setLayoutY(20);
    }

    // Init the Field
    public void createField(){
        gameStack.getChildren().add(mainPane);
    }

    public void addStone(int x, int y,
                         boolean magnetOnTop, boolean magnetOnBottom, boolean magnetOnRight, boolean magnetOnLeft){
        int tmpWidth = screenWidth/fieldWidth;
        int tmpHeight = screenHeight/fieldHeight;
        Rectangle stone = new Rectangle(tmpWidth, tmpHeight, Color.ORANGE );
        stone.setStroke(Color.DARKORANGE);
        addNode(stone, x, y);

        int x1 = x * tmpWidth;
        int y1 = (fieldHeight-y) * tmpHeight;
        int offset = 1;
        if(magnetOnTop) addMagnet(x1+offset, y1+offset, x1+tmpWidth-offset, y1+offset);
        if(magnetOnBottom) addMagnet(x1+offset, y1+tmpHeight-offset, x1+tmpWidth-offset, y1+tmpHeight-offset);
        if(magnetOnRight) addMagnet(x1+tmpWidth-offset, y1+offset, x1+tmpWidth-offset, y1+tmpHeight-offset);
        if(magnetOnLeft) addMagnet(x1+offset, y1+offset, x1+offset, y1+tmpHeight-offset);

    }

    private void addMagnet(int x, int y, int x1, int y1){
        Line line = new Line(x, y, x1, y1);
        line.setStrokeWidth(3);
        line.setStroke(Color.RED);
        mainPane.getChildren().add(line);
    }

    public void addPlayer(int x, int y, Color color){
        Group g = new Group();
        Rectangle player = new Rectangle(screenWidth/fieldWidth, screenHeight/fieldHeight, color );
        ImageView imageView = new ImageView();
        imageView.setFitWidth(screenWidth/fieldWidth);
        imageView.setFitHeight(screenHeight/fieldHeight);
        Image image = new Image("ressources/player.png");
        imageView.setImage(image);
        g.getChildren().addAll(player, imageView);
        //player.setStroke(Color.LIGHTGRAY);
        //playerList.add(player);
        playerList.add(g);

        //addNode(player, x, y);
        //addNode(imageView, x, y);
        addNode(g, x, y);
    }

    public void addCoin(int x, int y){
        int tmp = (screenWidth/fieldWidth)/2;
        Circle circle = new Circle(tmp, Color.GOLD);
        circle.setStroke(Color.DARKGOLDENROD);
        int posX = x * (screenWidth/fieldWidth) - tmp;
        int posY = (fieldHeight-y) * (screenHeight/fieldHeight) + tmp;
        circle.setLayoutX(posX);
        circle.setLayoutY(posY);
        mainPane.getChildren().add(circle);
    }

    // Help Method Add Node
    public void addNode(Node node, int x, int y){
        int posX = x * (screenWidth/fieldWidth);
        int posY = (fieldHeight-y) * (screenHeight/fieldHeight);
        node.setLayoutX(posX);
        node.setLayoutY(posY);
        mainPane.getChildren().add(node);
    }






    // ------------------------------------------------------------------------------------
    // Player Movement

    // Actions on the Field
    public void move(int playerId, Movement movement){
        int tmpWidth = screenWidth/fieldWidth;
        int tmpHeight = screenHeight/fieldHeight;
        Node player = playerList.get(playerId-1);
        Duration duration = Duration.millis(200);
        TranslateTransition tt = null;
        //for (Movement m : movements){
            switch (movement){
                case UP:
                    tt = new TranslateTransition(duration, player);
                    tt.setByY(-tmpHeight);
                    break;
                case DOWN:
                    tt = new TranslateTransition(duration, player);
                    tt.setByY(tmpHeight);
                    break;
                case LEFT:
                    tt = new TranslateTransition(duration, player);
                    tt.setByX(-tmpWidth);
                    break;
                case RIGHT:
                    tt = new TranslateTransition(duration, player);
                    tt.setByX(tmpWidth);
                    break;
                case MAGNET:
                    break;
                case JUMP:
                    tt = new TranslateTransition(duration, player);
                    tt.setByY(-tmpHeight);
                    break;
                case FALL:
                    tt = new TranslateTransition(duration, player);
                    break;

            }
            if (tt != null) currentTransition.getChildren().add(tt);
        //}
    }

    public void startAnimation(){
        currentTransition.setOnFinished(event -> currentTransition = new ParallelTransition());
        currentTransition.play();
    }
}
