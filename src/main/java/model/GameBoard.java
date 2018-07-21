package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard {
    private int width;
    private int height;
    private int[][] occupationMatrix;

    private List<Bar> bars;
    private List<Player> players;

    public GameBoard() {
        this.width = ThreadLocalRandom.current().nextInt(10, 30);
        this.height = ThreadLocalRandom.current().nextInt(10, 30);
        this.occupationMatrix = new int[height][width];
    }

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.occupationMatrix = new int[width][height];
    }


    // TODO improve
    public List<Bar> generateDimensionsAndBars() {
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

        result.add(player1);
        result.add(player2);

        return result;
    }


    public void setBars(List<Bar> bars) {
        this.bars = bars;
        for (Bar bar : bars) {
            for (Unit coordinate : bar.getUnits()) {
                occupationMatrix[coordinate.y()][coordinate.x()] = 1;
            }
        }
    }

    public void setPlayers(List<Player> players) {
        this.players = players;

        for (Player player : players) {
            occupationMatrix[player.y()][player.x()] = 2;
        }
    }

    public void movePlayer(String playerId, Movement movement, int times) {
        Player player = findPlayer(playerId);
        if (player == null) {
            return;
        }

        while (times-- > 0) {
            removePlayerDataFromMatrix(player);
            switch (movement) {
                case UP:
                    if (player.y() < (height - 1) && occupationMatrix[player.y() + 1][player.x()] == 0) {
                        player.up();
                    }
                    break;
                case DOWN:
                    if (player.y() > 0 && occupationMatrix[player.y() - 1][player.x()] == 0) {
                        player.down();
                    }
                    break;
                case LEFT:
                    if (player.x() > 0 && occupationMatrix[player.y()][player.x() - 1] == 0) {
                        player.left();
                    }
                    break;
                case RIGHT:
                    if (player.x() < (width - 1) && occupationMatrix[player.y()][player.x() + 1] == 0) {
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
            addPlayerDataToMatrix(player);
        }
    }

    public void removePlayerDataFromMatrix(Player player) {
        occupationMatrix[player.y()][player.x()] = 0;
    }

    public void addPlayerDataToMatrix(Player player) {
        occupationMatrix[player.y()][player.x()] = 2;
    }

    public void printElements() {
        for (int i = occupationMatrix.length - 1; i >= 0; i--) {
            int[] line = occupationMatrix[i];
            for (int unit : line) {
                System.out.print(unit + " ");
            }

            System.out.println();
        }
        System.out.println();
    }

    private Player findPlayer(String id) {
        for (Player player : players) {
            if (player.getId().equals(id)) {
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

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setBars(gameBoard.generateDimensionsAndBars());
        gameBoard.setPlayers(gameBoard.generatePlayers());
        gameBoard.printElements();
        gameBoard.movePlayer(gameBoard.getPlayers().get(0).getId(), Movement.UP, 3);
        gameBoard.movePlayer(gameBoard.getPlayers().get(0).getId(), Movement.RIGHT, 4);
        gameBoard.movePlayer(gameBoard.getPlayers().get(0).getId(), Movement.DOWN, 2);
        gameBoard.movePlayer(gameBoard.getPlayers().get(0).getId(), Movement.RIGHT, 4);
        gameBoard.printElements();

        gameBoard.movePlayer(gameBoard.getPlayers().get(1).getId(), Movement.LEFT, 4);
        gameBoard.movePlayer(gameBoard.getPlayers().get(1).getId(), Movement.UP, 6);
        gameBoard.printElements();
    }


}
