package demo;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import util.GameFieldState;
import model.Movement;

public class MovementDemo {
    // Attribute
    private GameFieldState gfs;


    // Constructor
    public MovementDemo(GameFieldState gfs) {
        this.gfs = gfs;
//        gfs.getKeyEvent().addListener((observable, oldValue, newValue) -> {
//            System.out.println("Pressed: " + newValue.getCode().getName());
//            switch (newValue.getCode()) {
//                case UP:
//                   gfs.move(0, Movement.UP);
//                    break;
//                case RIGHT:
//                    gfs.move(0, Movement.RIGHT);
//                    break;
//                case DOWN:
//                    gfs.move(0, Movement.DOWN);
//                    break;
//                case LEFT:
//                    gfs.move(0, Movement.LEFT);
//                    break;
//                case M:
//                    gfs.move(0, Movement.MAGNET);
//                    break;
//                case J:
//                    gfs.move(0, Movement.JUMP);
//                    break;
//            }
//        });
    }

    public void start(){
        gfs.setFieldWidth(16);
        gfs.setFieldHeight(16);
        gfs.setStone(0,3, false, false, false, false);
        gfs.setStone(1,3, false, false, false, false);
        gfs.setStone(2,3, false, false, false, false);
        gfs.setStone(5,2, true, false, false, true);
        gfs.setStone(6,2, false, false, false, false);
        gfs.setStone(7,2, false, false, false, false);
        gfs.setStone(4,5, false, true, false, false);
        gfs.setStone(5,5, false, true, false, false);
        gfs.setStone(3,5, false, true, false, false);
        gfs.setStone(2,5, false, true, false, false);

        //Vertikale
        gfs.setStone(6,7, false, false, false, true);
        gfs.setStone(6,8, false, false, false, true);
        gfs.setStone(6,9, false, false, false, true);
        gfs.setStone(6,10, true, false, false, true);
        // horizontale
        gfs.setStone(7,10, false, false, false, false);
        gfs.setStone(8,10, false, false, false, false);
        gfs.setStone(9,10, false, false, false, false);
        gfs.setStone(10,10, false, false, false, false);
        gfs.setStone(11,10, false, false, false, false);


        gfs.setCoin(12, 11 );
        gfs.setPlayer(1, 1, Color.BLUE);
        gfs.setPlayer(3, 1, Color.GREEN);
        gfs.createField();


    }


    public void onKeyPressed(KeyEvent kep){
            System.out.println("Pressed: " + kep.getCode().getName());
            switch (kep.getCode()) {
                // Player 1
                case UP:
                   gfs.move(1, Movement.UP);
                    break;
                case RIGHT:
                    gfs.move(1, Movement.RIGHT);
                    break;
                case DOWN:
                    gfs.move(1, Movement.DOWN);
                    break;
                case LEFT:
                    gfs.move(1, Movement.LEFT);
                    break;
                case M:
                    gfs.move(1, Movement.MAGNET);
                    break;
                case J:
                    gfs.move(1, Movement.JUMP);
                    break;

                // Player 2
                case W:
                   gfs.move(2, Movement.UP);
                    break;
                case D:
                    gfs.move(2, Movement.RIGHT);
                    break;
                case S:
                    gfs.move(2, Movement.DOWN);
                    break;
                case A:
                    gfs.move(2, Movement.LEFT);
                    break;
                case F:
                    gfs.move(2, Movement.MAGNET);
                    break;
                case R:
                    gfs.move(2, Movement.JUMP);
                    break;
            }
            gfs.endPhase();
    }
}
