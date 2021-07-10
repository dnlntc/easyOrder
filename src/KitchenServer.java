import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class KitchenServer {
    private ServerSocket kitchenServerSocket;
    private Socket waiterClientSocket;
    private int serverPort;

    public KitchenServer(int port ){
        this.serverPort=port;
        System.out.println("Starting kitchen server on port: "+port );
        runServer();
    }

    public void runServer(){
        String waiterCode;
        try {
            kitchenServerSocket =new ServerSocket(serverPort);
            while(true){
                waiterClientSocket = kitchenServerSocket.accept();
                waiterCode= waiterClientSocket.getRemoteSocketAddress().toString();
                System.out.println("Accepted connection from client "+waiterCode);
                KitchenWaitersManager kitchenWaitersManager = new KitchenWaitersManager(waiterClientSocket);
                Thread t = new Thread(kitchenWaitersManager, waiterCode);
                t.start();
            }

        } catch (IOException e) {
            System.out.println("ERROR. Problem on starting server on port: "+ serverPort+"----"+ kitchenServerSocket.getInetAddress());
            e.printStackTrace();
        }


    }
}
