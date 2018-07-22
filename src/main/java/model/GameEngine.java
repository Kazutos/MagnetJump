package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.UnitType.SPACE;

public class GameEngine {
    public static int NEXT_PLAYER_ID = 0;

    private GameBoard gameBoard;

    public GameEngine(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public static void main(String[] args) {
        GameEngine engine = new GameEngine(new GameBoard());
        engine.getGameBoard().setBars(engine.generateBars());
        engine.getGameBoard().setPlayers(engine.generatePlayers());
        engine.getGameBoard().printElements();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String str = scanner.next();
            switch (str) {
                case "u":
                    engine.movePlayer(engine.getGameBoard().getPlayers().get(0).getId(), Movement.UP, 1);
                    break;
                case "d":
                    engine.movePlayer(engine.getGameBoard().getPlayers().get(0).getId(), Movement.DOWN, 1);
                    break;
                case "l":
                    engine.movePlayer(engine.getGameBoard().getPlayers().get(0).getId(), Movement.LEFT, 1);
                    break;
                case "r":
                    engine.movePlayer(engine.getGameBoard().getPlayers().get(0).getId(), Movement.RIGHT, 1);
                    break;
                default:
                    break;
            }
            engine.getGameBoard().printElements();
        }
    }

    // TODO improve
    public List<Bar> generateBars() {
        List<Bar> result = new ArrayList<>();

        Bar bar1 = new Bar(0, 5, 4, BarPosition.HORIZONTAL);
        Bar bar2 = new Bar(4, 7, 3, BarPosition.VERTICAL);
        Bar bar3 = new Bar(9, 9, 4, BarPosition.HORIZONTAL);

        result.add(bar1);
        result.add(bar2);
        result.add(bar3);

        return result;
    }

    // TODO improve
    public List<Player> generatePlayers() {
        List<Player> result = new ArrayList<>();
        Player player1 = new Player(4, 0, "BLUE");
        Player player2 = new Player(6, 0, "RED");
        Player player3 = new Player(8, 0, "YELLOW");

        result.add(player1);
        result.add(player2);
        result.add(player3);

        return result;
    }

    public void addPlayer(int x, int y, int playerId) {
        Player player = new Player(x, y, "BLUE");
        player.setId(playerId);
        gameBoard.addPlayer(player);
    }

    public void addBarUnit(int x, int y) {
        gameBoard.addBarUnit(x, y);
    }


    public int[] movePlayer(int playerId, Movement movement, int times) {
        Player player = gameBoard.findPlayer(playerId);
        if (player == null) {
            return new int[]{0, 0};
        }

        int initX = player.x() + 1;
        int initY = player.y();

        while (times-- > 0) {
            gameBoard.removePlayerDataFromMatrix(player);
            Unit[][] unitMatrix = gameBoard.getUnitMatrix();
            switch (movement) {
                case UP:
                    if (player.y() < (gameBoard.getHeight() - 1)) {
                        if (SPACE.equals(unitMatrix[player.y() + 1][player.x()].getType())) {
                            player.up();
                        } else if (unitMatrix[player.y() + 1][player.x()].getMagnets()[2] != 1) {
                            movePlayer(playerId, Movement.DOWN, player.y());
                        }
                    }
                    break;
                case DOWN:
                    if (player.y() > 0 && SPACE.equals(unitMatrix[player.y() - 1][player.x()].getType())) {
                        player.down();
                    }
                    break;
                case LEFT:
                    if (player.x() > 0 && SPACE.equals(unitMatrix[player.y()][player.x() - 1].getType())) {
                        player.left();
                    }
                    break;
                case RIGHT:
                    if (player.x() < (gameBoard.getWidth() - 1) && SPACE.equals(unitMatrix[player.y()][player.x() + 1].getType())) {
                        player.right();
                    }
                    break;
                case JUMP:
                    //TODO
                    break;
                case MAGNET:
                    //TODO
                    break;
            }

            gameBoard.addPlayerDataToMatrix(player);
        }

        return new int[]{Math.abs(player.x() - initX), Math.abs(player.y() - initY)};

    }
}
