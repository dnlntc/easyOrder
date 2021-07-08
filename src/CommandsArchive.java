import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommandsArchive {
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

}
