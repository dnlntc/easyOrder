import java.io.*;
import java.util.Scanner;

public class CommandsArchiveManager {
    public boolean saveArchiveToStorage (CommandsArchive archive)
    {
        try {
            var fos = new FileOutputStream(archive.getDate()+"_archiveCommands.dat");
            var os = new ObjectOutputStream(fos);

            os.writeObject(archive);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public CommandsArchive loadArchiveFromStorage(String fileName)
    {
        CommandsArchive archiveRestored =null;

        try {
            var fin = new FileInputStream(fileName);
            var ois = new ObjectInputStream(fin);
            archiveRestored = (CommandsArchive) ois.readObject();
            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return archiveRestored;
    }
}
