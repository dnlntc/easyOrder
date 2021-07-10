import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommandsArchive implements Serializable {


    private Date date;
    private ArrayList<Commands> totalCommands;
    private String waiter;

    public String getWaiter() {
        return waiter;
    }

    public CommandsArchive(String waiterTabletAddress){
        date= Calendar.getInstance().getTime();
        waiter = waiterTabletAddress;
        totalCommands = new ArrayList<>();

    }

    public void add(Commands c) {
        totalCommands.add(c);
    }
    public ArrayList<Commands> getTotalCommands(){
        return totalCommands;
    }
    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public String toString() {
        return totalCommands.toString();
    }
}
