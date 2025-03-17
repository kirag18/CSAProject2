import java.util.ArrayList;
import java.util.Scanner;
public class Logic {
    private Shapes [][] grid;
    private Shapes [] answer;
    private Scanner scan;
    private Player person;
    private int tries;

    public Logic(){
        grid = new Shapes[8][4];
        tries = 8;
        scan = new Scanner(System.in);
        person = new Player(/*name here*/);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("orange");
        colors.add("yellow");
        colors.add("green");
        colors.add("blue");
        colors.add("pink");
        for (int)
        int first = (int)(Math.random()*6);
        answer[0] = new Shapes(colors.remove(first));




    }
}
