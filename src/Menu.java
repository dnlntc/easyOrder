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
                System.out.println("-------->MENU<--------'");
                System.out.println(" (1) Enter new order ");
                System.out.println(" (2) Order list ");
                System.out.println(" (3) End of work shift and save commands to storage");
                System.out.println(" (4) Reload command from storage");
                System.out.println(" (5) Get account for table number");
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
                        System.out.println("Getting order list for waiter:" + archive.getWaiter());
                        System.out.println(archive.getTotalCommands().toString());
                        break;
                    case 3:
                        to_server.println(choice);
                        to_server.flush();
                        answer_fromServer = (String) in.readObject();
                        System.out.println(answer_fromServer);
                        break;
                    case 4:
                        System.out.println("Please insert the name of commands archive to restore EX:'yyyy-mm-dd_archiveCommands.dat' \n -->");
                        Scanner scanName = new Scanner(System.in);
                        String fileName=scanName.nextLine();
                        to_server.println(choice);
                        to_server.flush();
                        out.writeObject(fileName);
                        out.flush();
                        answer_fromServer = (String) in.readObject();
                        System.out.println(answer_fromServer);
                        break;
                    case 5:
                        to_server.println(choice);
                        to_server.flush();

                        System.out.println("Please insert the TABLE NUMBER for which you want to have the account -->");
                        Scanner scanTableNumber = new Scanner(System.in);
                        int tableNumber=scanTableNumber.nextInt();

                        out.writeObject(tableNumber);
                        out.flush();

                        Commands commands = (Commands) in.readObject();
                        System.out.println("YOUR ACCOUNT: \n\n" + commands.toString() +" "+ commands.getTotalPrice());
                        break;
                    case 0:
                        System.out.println("Closing connection with kitchen server: "+server);
                        to_server.println(choice);
                        to_server.flush();
                        server.close();
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
        int choice;
        boolean repeat= true;
        while(repeat) {
            int menuSection=renderOptions();
            Scanner inp = new Scanner(System.in);
            switch (menuSection) {
                case 1:
                    System.out.println(" (1) Mineral water 1.50$");
                    System.out.println(" (2) Sparkling water 2.00$");
                    System.out.println(" (3) Coca-Cola 2.50$");
                    System.out.println(" (4) Wine 5.00$");
                    System.out.print(" Choice: ");
                    choice= inp.nextInt();
                    newCommand.addBeverage(choice);
                    newCommand.setTotalPrice(getPrice(menuSection,choice));
                    break;
                case 2:
                    System.out.println(" (1) Bruschette 4.50$");
                    System.out.println(" (2) Patatine fritte 3.00$");
                    System.out.println(" (3) Insalata 5.00$");
                    System.out.println(" (4) Panelle 5.00$");
                    System.out.print(" Choice: ");
                    choice= inp.nextInt();
                    newCommand.addStarter(choice);
                    newCommand.setTotalPrice(getPrice(menuSection,choice));
                    break;
                case 3:
                    System.out.println(" (1) Pasta alla Carbonara 9.00$");
                    System.out.println(" (2) Pasta al ragù 8.00$");
                    System.out.println(" (3) Casarecce al pistacchio 10.00$");
                    System.out.println(" (4) Tortellini in brodo 7.00$");
                    System.out.print(" Choice: ");
                    choice= inp.nextInt();
                    newCommand.addMain(choice);
                    newCommand.setTotalPrice(getPrice(menuSection,choice));
                    break;
                case 4:
                    System.out.println(" (1) Cotoletta alla milanese 7.00$");
                    System.out.println(" (2) Grigliata mista 15.00$");
                    System.out.println(" (3) Filetto con contorno di insalata 12.00$");
                    System.out.println(" (4) Involtini alla messinese 13.00$");
                    System.out.print(" Choice: ");
                    choice= inp.nextInt();
                    newCommand.addSecond(choice);
                    newCommand.setTotalPrice(getPrice(menuSection,choice));
                    break;
                case 5:
                    System.out.println(" (1) Gelo alla cannella 7.00$");
                    System.out.println(" (2) Sorbetto al limone 4,50$");
                    System.out.println(" (3) Cuore caldo al cioccolato 6.00$");
                    System.out.println(" (4) Frutta fresca e gelato 4.00$");
                    System.out.print(" Choice: ");
                    choice= inp.nextInt();
                    newCommand.addDessert(choice);
                    newCommand.setTotalPrice(getPrice(menuSection,choice));
                    break;
                case 0:
                    System.out.println("Sending order to the kitchen..");
                    repeat =false;
            }
        }
        return newCommand;
    }

    private float getPrice(int menuSelection, int numOrder)
    {
        float totalPrice=0;
        switch (menuSelection)
        {
            case 1:
                if(numOrder ==1)
                    totalPrice+=1.50;
                else if(numOrder ==2)
                    totalPrice+=2.50;
                else if(numOrder==3)
                    totalPrice+=3.50;
                else if(numOrder==4)
                    totalPrice+=5.00;
                else
                    totalPrice+=5.00;
                break;
            case 2:
                if(numOrder ==1)
                    totalPrice+=1.50;
                else if(numOrder ==2)
                    totalPrice+=2.50;
                else if(numOrder==3)
                    totalPrice+=3.50;
                else if(numOrder==4)
                    totalPrice+=5.00;
                else
                    totalPrice+=5.00;
                break;
            case 3:
                if(numOrder ==1)
                    totalPrice+=1.50;
                else if(numOrder ==2)
                    totalPrice+=2.50;
                else if(numOrder==3)
                    totalPrice+=3.50;
                else if(numOrder==4)
                    totalPrice+=5.00;
                else
                    totalPrice+=5.00;
                break;
            case 4:
                if(numOrder ==1)
                    totalPrice+=1.50;
                else if(numOrder ==2)
                    totalPrice+=2.50;
                else if(numOrder==3)
                    totalPrice+=3.50;
                else if(numOrder==4)
                    totalPrice+=5.00;
                else
                    totalPrice+=5.00;
                break;
            case 5:
                if(numOrder ==1)
                    totalPrice+=1.50;
                else if(numOrder ==2)
                    totalPrice+=2.50;
                else if(numOrder==3)
                    totalPrice+=3.50;
                else if(numOrder==4)
                    totalPrice+=5.00;
                else
                    totalPrice+=5.00;
                break;
            case 0:
                totalPrice=0;
                break;
        }
        return totalPrice;
    }
}
