package server;

import util.HelpConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class InputThread implements Runnable {
    private Thread t;
    private String threadName;
    public Socket client;
    public String[] players;

    InputThread(String name, Socket c, String[] players) {
      threadName = name;
      client = c;
      this.players = players;
   }

   public void run() {
            try {
                BufferedReader client_rd = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter client_wr = new PrintWriter(client.getOutputStream(), true);

                String temp = client_rd.readLine();
                client_wr.println("HTTP/1.1 200 OK\r\nContent-Length: 38\r\nContent-Type: text/plain\r\n\r\nGame already going, sorry, you're late");
                while (temp != null) {
                    if (temp.startsWith("GET")) break;
                    if (temp.startsWith("control")) {
                        int playerNum = -1;
                        for (int i = 0; i < 4; i++) {
                            if (client.getInetAddress().toString().equals(players[i])) {
                                playerNum = i+1;
                                System.out.print("Player " + playerNum + " sent ");
                                break;
                            }
                        }
                        System.out.println(temp);
                        command(playerNum, temp);

                    }

                    temp = client_rd.readLine();
                }

                client_wr.close();
                client_rd.close();
                client.close();
            } catch (IOException ex) {
                Logger.getLogger(InputThread.class.getName()).log(Level.SEVERE, null, ex);
            }



}

   public void command(int playerId, String command){
       HelpConnection hc = HelpConnection.getInstance();
        switch (command) {
                // Player 1
                case "control up":
                   hc.runCommand(playerId, model.Movement.UP);
                    break;
                case "control right":
                    hc.runCommand(playerId, model.Movement.RIGHT);
                    break;
                case "control down":
                    hc.runCommand(playerId, model.Movement.DOWN);
                    break;
                case "control left":
                    hc.runCommand(playerId, model.Movement.LEFT);
                    break;
                case "control A":
                    hc.runCommand(playerId, model.Movement.MAGNET);
                    break;
                case "control B":
                    hc.runCommand(playerId, model.Movement.JUMP);
                    break;
        }
   }

   public void start () {
      if (t == null) {
         t = new Thread (this, threadName);
         t.start();
      }
   }
}
