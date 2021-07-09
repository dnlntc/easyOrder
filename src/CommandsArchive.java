import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommandsArchive implements Serializable {
    private Calendar date;
    private ArrayList<Commands> totalCommands;
    private String waiter;

    public CommandsArchive(String waiterTabletAddress){
        date= Calendar.getInstance();
        waiter = waiterTabletAddress;
        totalCommands = new ArrayList<>();

    }
    public void add(Commands c) {
        totalCommands.add(c);
    }
    public ArrayList<Commands> getTotalCommands(){
        return totalCommands;
        /*for (Commands c: totalCommands) {
            System.out.println("---> "+c);
        }*/
    }

    @Override
    public String toString() {
        return totalCommands.toString();
    }
}
