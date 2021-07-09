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
            //PrintWriter to_waiter =new PrintWriter(waiter.getOutputStream());

            ObjectOutputStream out = new ObjectOutputStream(waiter.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(waiter.getInputStream());
            boolean go_on=true;
            Commands command_fromWaiter;
            int choice;
            CommandsArchiveManager archiveManager =new CommandsArchiveManager();

            while(go_on){
                choice = from_waiter.nextInt();
                switch (choice){
                    case 1:
                        command_fromWaiter =(Commands) in.readObject();
                        archive.add(command_fromWaiter);
                        out.writeObject("OK");
                        out.flush();
                        out.reset();
                        System.out.println("ORDER FROM WAITER:"+archive.getWaiter()+"INSERTED");
                        break;
                    case 2:
                        out.writeObject(archive);
                        out.flush();
                        out.reset();
                        System.out.println("ORDER LIST SEND TO:"+waiter);
                        break;
                    case 3:
                        archiveManager.saveArchiveToStorage(archive);
                        out.writeObject(archiveManager.saveArchiveToStorage(archive)?"ORDER LIST SAVED ON STORAGE":"ERROR DURING SAVING ARCHIVE");
                        out.flush();
                        out.reset();
                        break;
                    case 0:
                        try {
                            System.out.println("Request to close connection from:" + waiter);
                            waiter.close();
                            System.out.println("System CLOSED");
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
