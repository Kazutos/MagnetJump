package interfaces;

import javafx.scene.paint.Color;

public interface Field {

    public void setFieldWidth();
    public void setFieldHeight();

    public void setStone(int x, int y,
                         boolean magnetOnTop, boolean magnetOnBottom,
                         boolean magnetOnRight, boolean magnetLeft);
    public void setPlayer(int x, int y, int playerId, Color color);
    public void setCoin(int x, int y);


}
