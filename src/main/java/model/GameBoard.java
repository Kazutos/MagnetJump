package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static model.UnitType.*;

public class GameBoard {
    private static final int MIN_HEIGHT = 10;
    private static final int MAX_HEIGHT = 15;
    private static final int MIN_WIDTH = 10;
    private static final int MAX_WIDTH = 15;

    private int width;
    private int height;
    private Unit[][] unitMatrix;
    private Unit target;

    private List<Bar> bars;
    private List<Player> players;

    public GameBoard() {
        this.width = ThreadLocalRandom.current().nextInt(MIN_WIDTH, MAX_WIDTH);
        this.height = ThreadLocalRandom.current().nextInt(MIN_HEIGHT, MAX_HEIGHT);
        initializeUnitMatrix(height, width);
        target = new Unit(7, height - 1, TARGET);
        this.unitMatrix[height - 1][7] = target;
    }

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        initializeUnitMatrix(height, width);
        target = new Unit(7, height - 1, TARGET);
        this.unitMatrix[height - 1][7] = target;
    }

    private void initializeUnitMatrix(int height, int width) {
        this.unitMatrix = new Unit[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                unitMatrix[i][j] = new Unit(j, i, SPACE);
            }
        }
    }

    public void setBars(List<Bar> bars) {
        this.bars = bars;
        for (Bar bar : bars) {
            for (Unit unit : bar.getUnits()) {
                unit.setType(BAR);
                unitMatrix[unit.y()][unit.x()] = unit;
            }
        }
    }

    public void setPlayers(List<Player> players) {
        this.players = players;

        for (Player player : players) {
            player.getUnit().setType(PLAYER);
            unitMatrix[player.y()][player.x()] = player.getUnit();
        }
    }

    public void movePlayer(int playerId, Movement movement, int times) {
        Player player = findPlayer(playerId);
        if (player == null) {
            return;
        }

        while (times-- > 0) {
            removePlayerDataFromMatrix(player);
            switch (movement) {
                case UP:
                    if (player.y() < (height - 1)) {
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
                    if (player.x() < (width - 1) && SPACE.equals(unitMatrix[player.y()][player.x() + 1].getType())) {
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

            // TODO remove prints (it was added for debugging purpose.)
            System.out.println(player.getUnit() + " " + target);
            if (player.getUnit().equals(target)) {
                System.out.println("YOU WIN!");
            }
            addPlayerDataToMatrix(player);
        }
    }

    public void removePlayerDataFromMatrix(Player player) {
        unitMatrix[player.y()][player.x()] = new Unit(player.y(), player.x(), SPACE);
    }

    public void addPlayerDataToMatrix(Player player) {
        unitMatrix[player.y()][player.x()] = player.getUnit();
    }

    public void printElements() {
        for (int i = unitMatrix.length - 1; i >= 0; i--) {
            Unit[] unitsRow = unitMatrix[i];
            for (Unit unit : unitsRow) {
                System.out.print(unit.getType() + " ");
            }

            System.out.println();
        }
        System.out.println();
    }

    private Player findPlayer(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }

        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Bar> getBars() {
        return bars;
    }

    // TODO improve
    public List<Bar> generateBars() {
        List<Bar> result = new ArrayList<>();

        Bar bar1 = new Bar(0, 5, 4, BarPosition.HORIZONTAL);
        Bar bar2 = new Bar(4, 7, 3, BarPosition.VERTICAL);
        Bar bar3 = new Bar(width - 5, height - 5, 4, BarPosition.HORIZONTAL);

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

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setBars(gameBoard.generateBars());
        gameBoard.setPlayers(gameBoard.generatePlayers());
        gameBoard.printElements();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String str = scanner.next();
            switch (str) {
                case "u":
                    gameBoard.movePlayer(gameBoard.getPlayers().get(0).getId(), Movement.UP, 1);
                    break;
                case "d":
                    gameBoard.movePlayer(gameBoard.getPlayers().get(0).getId(), Movement.DOWN, 1);
                    break;
                case "l":
                    gameBoard.movePlayer(gameBoard.getPlayers().get(0).getId(), Movement.LEFT, 1);
                    break;
                case "r":
                    gameBoard.movePlayer(gameBoard.getPlayers().get(0).getId(), Movement.RIGHT, 1);
                    break;
            }
            gameBoard.printElements();
        }

//        gameBoard.movePlayer(gameBoard.getPlayers().get(0).getId(), Movement.UP, 3);
//        gameBoard.movePlayer(gameBoard.getPlayers().get(0).getId(), Movement.RIGHT, 4);
//        gameBoard.movePlayer(gameBoard.getPlayers().get(0).getId(), Movement.DOWN, 2);
//        gameBoard.movePlayer(gameBoard.getPlayers().get(0).getId(), Movement.RIGHT, 4);
//        gameBoard.printElements();
//
//        gameBoard.movePlayer(gameBoard.getPlayers().get(1).getId(), Movement.LEFT, 4);
//        gameBoard.movePlayer(gameBoard.getPlayers().get(1).getId(), Movement.UP, 6);
//        gameBoard.printElements();
    }


}
