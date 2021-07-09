import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Menu {

    public void renderMenu (Socket server) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());
            //Scanner from_server = new Scanner(server.getInputStream());
            PrintWriter to_server = new PrintWriter(server.getOutputStream());
            Scanner input_fromUser = new Scanner(System.in);
            String answer_fromServer;
            boolean go_on = true;

            while(go_on)
            {
                System.out.println("MENU'");
                System.out.println(" (1) Enter new order ");
                System.out.println(" (2) Order list ");
                System.out.println(" (3) End of work shift");
                System.out.println(" (0) Close Easy Order environment");
                System.out.println("-----------------------------------------");
                System.out.print(" Insert choice:");
                int choice= input_fromUser.nextInt();
                switch (choice)
                {
                    case 1:
                        var toSend = insert_newOrder();
                        System.out.println("Sending order to kitchen server" + server.getRemoteSocketAddress());
                        to_server.println(choice);
                        to_server.flush();
                        out.writeObject(toSend);
                        out.flush();
                        answer_fromServer = (String) in.readObject();
                        System.out.println(answer_fromServer);
                        break;
                    case 2:
                        to_server.println(choice);
                        to_server.flush();
                        CommandsArchive archive = (CommandsArchive) in.readObject();
                        System.out.println(archive.getTotalCommands().toString());

                        break;
                    case 3:
                        System.out.println("END OF WORK SHIFT NOT IMPLEMENTED!");
                        break;
                    case 0:
                        //to_server.println("COMMAND_QUIT");
                        go_on=false;
                        break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private int renderOptions() {
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
    private Commands insert_newOrder() {
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
                    newCommand.addBeverage(inp.nextInt());
                    break;
                case 2:
                    System.out.println(" (1) Bruschette");
                    System.out.println(" (2) Patatine fritte ");
                    System.out.println(" (3) Insalata ");
                    System.out.println(" (4) Panelle");
                    System.out.print(" Choice: ");
                    newCommand.addStarter(inp.nextInt());
                    break;
                case 3:
                    System.out.println(" (1) Pasta alla Carbonara ");
                    System.out.println(" (2) Pasta al ragù ");
                    System.out.println(" (3) Casarecce al pistacchio ");
                    System.out.println(" (4) Tortellini in brodo");
                    System.out.print(" Choice: ");
                    newCommand.addMain(inp.nextInt());
                    break;
                case 4:
                    System.out.println(" (1) Cotoletta alla milanese ");
                    System.out.println(" (2) Grigliata mista ");
                    System.out.println(" (3) Filetto con contorno di insalata ");
                    System.out.println(" (4) Involtini alla messinese");
                    System.out.print(" Choice: ");
                    newCommand.addSecond(inp.nextInt());
                    break;
                case 5:
                    System.out.println(" (1) Gelo alla cannella");
                    System.out.println(" (2) Sorbetto al limone");
                    System.out.println(" (3) Cuore caldo al cioccolato");
                    System.out.println(" (4) Frutta fresca e gelato");
                    System.out.print(" Choice: ");
                    newCommand.addDessert(inp.nextInt());
                    break;
                case 0:
                    System.out.println("Sending order to the kitchen..");
                    repeat =false;
            }
        }
        return newCommand;
    }
}
