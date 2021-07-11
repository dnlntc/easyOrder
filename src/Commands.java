import java.io.Serializable;
import java.util.ArrayList;

public class Commands implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private int tableNumber;
    private ArrayList<Integer> beverage;
    private ArrayList<Integer> starters;
    private ArrayList<Integer> mainCourse;
    private ArrayList<Integer> secondCourse;
    private ArrayList<Integer> desserts;

    public Commands(){
        beverage = new ArrayList<>();
        starters = new ArrayList<>();
        mainCourse = new ArrayList<>();
        secondCourse = new ArrayList<>();
        desserts = new ArrayList<>();
    }
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
    public void addBeverage(int b) {
        beverage.add(b);
    }
    public void addStarter(int b) {
        starters.add(b);
    }
    public void addMain(int b) {
        mainCourse.add(b);
    }
    public void addSecond(int b) {
        secondCourse.add(b);
    }
    public void addDessert(int b) {
        desserts.add(b);
    }
    public String getElementOfArray (ArrayList<Integer> array)
    {
        String toReturn=" ";
        for (Integer c: array) {
            toReturn= toReturn+"  "+c.toString();
        }
        return toReturn;
    }

    @Override
    public String toString() {
        return "Table number: "+ tableNumber+" Beverage selection: "+ getElementOfArray(beverage) + " Starters selection: " + getElementOfArray(starters) + " Main course selection: " + getElementOfArray(mainCourse) + " Second course selection: "+ getElementOfArray(secondCourse) + " Dessert selection: "+ getElementOfArray(desserts) +"\n\n";
    }
}
