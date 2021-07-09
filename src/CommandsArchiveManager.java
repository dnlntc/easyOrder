import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CommandsArchiveManager {
    public boolean saveArchiveToStorage (CommandsArchive archive)
    {
        try {
            var fos = new FileOutputStream("archive.dat");
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
}
