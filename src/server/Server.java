package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author basil
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
            try {
                String[] players = new String[4];
                
                System.out.println("Server started...");
                
                // Socket to accept players and send them controllers //
                ServerSocket playersSocket = new ServerSocket(8080);
                
                
                // Accept players and send them Hi message //
                for (int i = 0; i < 2;) {
                    Socket player = playersSocket.accept();
                    
                    
                    // If its already a registered player, just give him the page again //
                    if (player.getInetAddress().toString().equals(players[0]) || player.getInetAddress().toString().equals(players[1]) || player.getInetAddress().toString().equals(players[2])) {
                        PrintWriter p1_out = new PrintWriter(player.getOutputStream(), true);

                        System.out.println("REPEATED REQUEST" + i);
                        String resp = "HTTP/1.1 200 OK\r\n\r\nHi";
                        p1_out.print(resp);
                        p1_out.close();
                        player.close();
                    } else {
                        PrintWriter p1_out = new PrintWriter(player.getOutputStream(), true);

                        System.out.println("Connection accepted from: " + player.getInetAddress().toString());
                        players[i] = player.getInetAddress().toString();
                        i++;

                        // Send the AJAX controller file to the player //
                        String ajaxFile = new String(Files.readAllBytes(Paths.get("src/server/ajax.html")));
                        
                        String resp_temp = "HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=utf-8\r\n\r\nHi, You are player #" + i + "\n\n\n" + ajaxFile;
                        String resp = "";
                        
                        if (i==1) resp = resp_temp.replace("PLAYERBACKGROUNDCOLOR", "#ff0000");
                        if (i==2) resp = resp_temp.replace("PLAYERBACKGROUNDCOLOR", "#00ff00");
                        if (i==3) resp = resp_temp.replace("PLAYERBACKGROUNDCOLOR", "#0000ff");
                        if (i==4) resp = resp_temp.replace("PLAYERBACKGROUNDCOLOR", "#eeeeee");
                        
                        p1_out.print(resp);
                        p1_out.close();
                        player.close();
                    }
                }
                
                playersSocket.close();
                
                System.out.println("All players are registered, the game has started!!");
                
                
                // Get AJAX messages for controls from the players, use previously stored IPs in 
                // 'players' array to check which player sent the request. 
                // Multithreads may be needed if requests are slow
                ServerSocket controlsSocket = new ServerSocket(8080);
                
                while (true) {
                    Socket client = controlsSocket.accept();
                    new Thread(new InputThread("t",client,players)).start();
                }
                
            } catch (IOException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
    }
    
}















