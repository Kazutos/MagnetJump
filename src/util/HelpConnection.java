package util;

import javafx.application.Platform;
import model.Movement;
import java.util.LinkedList;

public class HelpConnection {
    // Attribute
    public GameFieldState gameFieldState;
    public static HelpConnection helpConnection;
    public LinkedList<Command> queue;


    public static HelpConnection getInstance(){
        if(helpConnection == null){
            helpConnection = new HelpConnection();
        }
        return helpConnection;
    }



    // Constructor
    public HelpConnection() {
        this.queue = new LinkedList<>();
    }

    public void setGameFieldState(GameFieldState gameFieldState) {
        this.gameFieldState = gameFieldState;
    }

    private synchronized  void putCommand(Command command){
        queue.add(command);
    }

    public Command pullCommand(){
        Command tmp = null;
        if(queue.size() > 0){
            tmp = queue.getFirst();
            queue.removeFirst();
        }
        if(tmp != null)
            System.out.println("ID: " + tmp.getPlayerId() + " Commad: " + tmp.getMovement());
        return tmp;
    }


    public void runCommand(int playerId, Movement movement){
        System.out.println("runCommand");
        putCommand(new Command(playerId, movement));
    }
}
