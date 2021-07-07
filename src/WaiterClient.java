import java.net.Socket;

public class WaiterClient {
    String server_ipAddress;
    int server_port;
    Socket waiter_clientSocket;

    public WaiterClient(String ip, int port)
    {
        System.out.println("Welcome on easyOrder setting up connection with the kitchen..");
        this.server_ipAddress = ip;
        this.server_port = port;
        start();
    }
    public void start()
    {

    }

}
