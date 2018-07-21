import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

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
                        String ajaxFile = new String(Files.readAllBytes(Paths.get("ajax.html")));
                        
                        String resp = "HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=utf-8\r\n\r\nHi, You are player #" + i + "\n\n\n" + ajaxFile;
                        
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
                    // One of the players connects to the server //
                    Socket client = controlsSocket.accept();
                    BufferedReader client_rd = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter client_wr = new PrintWriter(client.getOutputStream(), true);
                    
                    String temp = client_rd.readLine();
                    client_wr.println("HTTP/1.1 200 OK\r\nContent-Length: 3\r\nContent-Type: text/plain\r\n\r\nSUP");
                    while (temp != null) {
                        if (temp.startsWith("GET")) break;
                        if (temp.startsWith("control")) {
                            for (int i = 0; i < 4; i++) {
                                if (client.getInetAddress().toString().equals(players[i])) {
                                    int playerNum = i+1;
                                    System.out.print("Player " + playerNum + " sent ");
                                }
                            }
                            System.out.println(temp);
                            
                        }
                        
                        temp = client_rd.readLine();
                    }
                        
                    client_wr.close();
                    client_rd.close();
                    client.close();
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
    }
    
}
