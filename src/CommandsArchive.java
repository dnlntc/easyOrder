import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CommandsArchive implements Serializable {
    private static final long serialVersionUID = 6529685098267757691L;
    private Date date;
    private ArrayList<Commands> totalCommands;
    private String waiter;

    public CommandsArchive(String waiterTabletAddress){
        date= Calendar.getInstance().getTime();
        waiter = waiterTabletAddress;
        totalCommands = new ArrayList<>();

    }

    public String getWaiter() {
        return waiter;
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
