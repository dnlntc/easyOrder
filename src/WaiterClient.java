import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WaiterClient {
    String server_ipAddress;
    int server_port;
    Socket waiter_clientSocket;

    public WaiterClient(String ip, int port)
    {
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
    public boolean renderMenu (Socket server)
    {
        try {
            Scanner from_server = new Scanner(server.getInputStream());
            var to_server = new PrintWriter(server.getOutputStream());
        Scanner input_fromUser = new Scanner(System.in);
        String answer_fromServer;
        System.out.println("MENU'");
        System.out.println(" (1) Enter new order ");
        System.out.println(" (2) Order list ");
        System.out.println(" (3) End of work shift");
        System.out.println(" (0) Close Easy Order environment");
        System.out.println("-----------------------------------------");
        System.out.print(" Insert choice:");
        switch (input_fromUser.nextInt())
        {
            case 1:
                var toSend = insert_newOrder();
                System.out.println("Sending order to kitchen server" + server.getRemoteSocketAddress());
                to_server.println(toSend);
                to_server.flush();
                // check for the answer
                answer_fromServer = from_server.nextLine();
                if (answer_fromServer.equals("OK")) {
                    System.out.println("Order correctly entered ");
                } else if (answer_fromServer.equals("ERROR")) {
                    System.out.println("Error, server reported an error while adding order");
                } else {
                    System.out.println("FATAL: unknown message protocol " + answer_fromServer);
                }
                break;
            case 2:
                System.out.println("ORDER LIST NOT IMPLEMENTED!");
                break;
            case 3:
                System.out.println("END OF WORK SHIFT NOT IMPLEMENTED!");
                break;
            case 0:
                to_server.println("COMMAND_QUIT");
                return false;
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return true;
    }
    public int renderOptions() {
        Scanner toReturn =new Scanner(System.in);
        System.out.println("-----------------------------------------");
        System.out.println(" (1) Beverage ");
        System.out.println(" (2) Starters ");
        System.out.println(" (3) Main Course ");
        System.out.println(" (4) Second Course");
        System.out.println(" (5) Dessert");
        System.out.println(" (0) SEND ORDER TO THE KITCHEN");
        System.out.println("-----------------------------------------");
        System.out.print("Choose one menù section: ");
        return toReturn.nextInt();
    }

    public Commands insert_newOrder()
    {
        Commands newCommand = new Commands();
        Scanner numTable = new Scanner(System.in);
        System.out.print("Insert Table number: ");
        newCommand.setTableNumber(numTable.nextInt());
        boolean repeat= true;
        while(repeat) {
            int menuSection=renderOptions();
            Scanner inp = new Scanner(System.in);
            switch (menuSection) {
                case 1:
                    System.out.println(" (1) Mineral water ");
                    System.out.println(" (2) Sparkling water ");
                    System.out.println(" (3) Coca-Cola ");
                    System.out.println(" (4) Wine");
                    System.out.print(" Choice: ");
                    newCommand.beverage.add(inp.nextInt());
                    break;
                case 2:
                    System.out.println(" (1) Bruschette");
                    System.out.println(" (2) Patatine fritte ");
                    System.out.println(" (3) Insalata ");
                    System.out.println(" (4) Panelle");
                    System.out.print(" Choice: ");
                    newCommand.starters.add(inp.nextInt());
                    break;
                case 3:
                    System.out.println(" (1) Pasta alla Carbonara ");
                    System.out.println(" (2) Pasta al ragù ");
                    System.out.println(" (3) Casarecce al pistacchio ");
                    System.out.println(" (4) Tortellini in brodo");
                    System.out.print(" Choice: ");
                    newCommand.mainCourse.add(inp.nextInt());
                    break;
                case 4:
                    System.out.println(" (1) Cotoletta alla milanese ");
                    System.out.println(" (2) Grigliata mista ");
                    System.out.println(" (3) Filetto con contorno di insalata ");
                    System.out.println(" (4) Involtini alla messinese");
                    System.out.print(" Choice: ");
                    newCommand.secondCourse.add(inp.nextInt());
                    break;
                case 5:
                    System.out.println(" (1) Gelo alla cannella");
                    System.out.println(" (2) Sorbetto al limone");
                    System.out.println(" (3) Cuore caldo al cioccolato");
                    System.out.println(" (4) Frutta fresca e gelato");
                    System.out.print(" Choice: ");
                    newCommand.desserts.add(inp.nextInt());
                    break;
                case 0:
                    System.out.println("Sending order to the kitchen..");
                    repeat =false;
            }
        }
        return newCommand;
    }
    public void start(Socket server)
    {
        try
        {
            boolean go_on = true;
            while (go_on) {
                go_on= renderMenu(server);
            }
            server.close();
            System.out.println("Quitting connection...");

        } catch (IOException e) {
            System.out.println("Cannot connect to server kitchen with IP: "+server_ipAddress);
            e.printStackTrace();
        }
    }

}
