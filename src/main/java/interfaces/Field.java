package interfaces;

import javafx.scene.paint.Color;

public interface Field {

    public void setFieldWidth(int width);
    public void setFieldHeight(int height);

    public void setStone(int x, int y,
                         boolean magnetOnTop, boolean magnetOnBottom,
                         boolean magnetOnRight, boolean magnetOnLeft);
    public void setPlayer(int x, int y, Color color);
    public void setCoin(int x, int y);


}
