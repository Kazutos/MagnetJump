import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
                
                System.out.println("HELLO");
                ServerSocket playersSocket = new ServerSocket(8080);
                
                Socket p1 = playersSocket.accept();
                PrintWriter p1_out = new PrintWriter(p1.getOutputStream(), true);
                BufferedReader p1_in = new BufferedReader(new InputStreamReader(p1.getInputStream()));
                
                String p1_req= "hi";
                while (p1_req.length() > 0) {
                    p1_req = p1_in.readLine();
                    System.out.println(p1_req);
                }
                
                // SEND THE WEBPAGE //
                String resp = "HTTP/1.1 200 OK\r\n\r\nHi";
                System.out.println(resp);
                p1_out.print(resp);
                p1_out.close();
                p1_in.close();
                p1.close();
                
                Socket p2 = playersSocket.accept();
                PrintWriter p2_out = new PrintWriter(p2.getOutputStream(), true);
                
                Socket p3 = playersSocket.accept();
                PrintWriter p3_out = new PrintWriter(p3.getOutputStream(), true);
                
                Socket p4 = playersSocket.accept();
                PrintWriter p4_out = new PrintWriter(p4.getOutputStream(), true);
                
                while (true) {
                    ServerSocket controlsSocket = new ServerSocket(80);
                }
            } catch (Exception e) {
                System.out.println("ERROR");
            }
    }
    
}
