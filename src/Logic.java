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
        for (int i = 0; i<4;i++){
            int idx = (int)(Math.random()*(6-i));
            answer[i] = new Shapes(colors.remove(idx));
        }
        play();

    }

    private void play(){
        System.out.println("Enter 4 UNIQUE colors ");
        String[] order = scan.nextLine().split(",");

    }
}
