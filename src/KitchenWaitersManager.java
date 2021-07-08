import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class KitchenWaitersManager implements Runnable {

    Socket waiter;
    CommandsArchive archive;
    public KitchenWaitersManager(Socket waiter){
        this.waiter=waiter;
        archive= new CommandsArchive(Thread.currentThread().getName());

    }
    @Override
    public void run() {
        try {
            Scanner from_waiter= new Scanner(waiter.getInputStream());
            PrintWriter to_waiter =new PrintWriter(waiter.getOutputStream());

            ObjectOutputStream out = new ObjectOutputStream(waiter.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(waiter.getInputStream());
            boolean go_on=true;
            Commands command_fromWaiter;
            command_fromWaiter =(Commands) in.readObject();
            int choice;
            while(go_on){
                choice = command_fromWaiter.selection;
                switch (choice){
                    case 1:
                        to_waiter.println("OK");
                        to_waiter.flush();
                        break;
                    case 2:
                        System.out.println("LISTA ORDINI EFFETTUATI - TO BE IMPLEMENTED");
                        break;
                    case 3:
                        System.out.println("END WORK SAVE ALL-  TO BE IMPLEMENTED");
                        break;
                    case 0:
                        try {
                            waiter.close();
                            go_on=false;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
