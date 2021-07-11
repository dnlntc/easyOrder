import java.io.IOException;
import java.net.Socket;


public class WaiterClient {
    String server_ipAddress;
    int server_port;
    Socket waiter_clientSocket;

    public WaiterClient(String ip, int port) {
        System.out.println("Welcome on easyOrder setting up connection with the kitchen..");
        this.server_ipAddress = ip;
        this.server_port = port;
        try {
            waiter_clientSocket = new Socket(server_ipAddress, server_port);
        } catch (IOException e) {
            System.out.println("ERROR ESTABLISH CONNECTION TO SERVER: "+ server_ipAddress);
            e.printStackTrace();
        }
        start(waiter_clientSocket);
     }
    public void start(Socket server) {
        Menu startMenu = new Menu();
        startMenu.renderMenu(server);
        System.out.println("Closing easyOrder...");

    }
}
