package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static model.UnitType.*;

public class GameBoard {
    private static final int MIN_HEIGHT = 16;
    private static final int MAX_HEIGHT = 17;
    private static final int MIN_WIDTH = 16;
    private static final int MAX_WIDTH = 17;

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
        this.bars = new ArrayList<>();
        this.players = new ArrayList<>();
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

    public Unit getTarget() {
        return target;
    }

    public void setTarget(Unit target) {
        this.target = target;
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

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addBarUnit(int x, int y) {
        unitMatrix[y][x] = new Unit(x, y, BAR);
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

    public Player findPlayer(int id) {
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Unit[][] getUnitMatrix() {
        return unitMatrix;
    }
}
