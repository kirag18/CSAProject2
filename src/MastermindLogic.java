import java.util.ArrayList;
import java.util.Scanner;

public class MastermindLogic {
    private Shapes [][] grid;
    private Shapes [] answer;
    private Scanner scan;
    private Player person;
    private int tries;
    private boolean win;

    public MastermindLogic(String name){
        grid = new Shapes[8][8];
        answer = new Shapes[4];
        win = false;

        tries = 8;
        scan = new Scanner(System.in);
        person = new Player(name);
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

    }

    public void playGame(){

        while (tries >0 || win){
            printGrid();
            int whites = 0;
            int reds = 0;
            System.out.println("Enter 4 UNIQUE colors ");//TODO: change this part when we do GUI
            String[] order = scan.nextLine().split(",");
            for (int i = 0; i<order.length;i++){
                for (int j = 0;j<answer.length;j++){
                    if (order[i].equals(answer[i].getColor())){
                        if (i==j){

                            reds++;
                        }else{
                            System.out.println("White: "+order[i]+" "+answer[i].getColor());
                            whites++;
                        }
                    }
                }
            }
            tries--;
            for (int i = 0;i<grid[0].length;i++){
                if (i<4){
                    grid[tries][i] = new Shapes(order[i]);
                }else{
                    if (reds>0){
                        grid[tries][i] = new Shapes("#R");
                        reds--;
                    } else if (whites>0) {
                        grid[tries][i] = new Shapes("#W");
                        whites--;
                    }else{
                        grid[tries][i] = new Shapes("##");
                    }
                }
            }
            if (reds == 4){
                win = true;
            }

        }



    }

    public void printGrid(){
        for (Shapes[] row : grid){
            for (Shapes space:row){
                if (space == null){
                    System.out.print("()");
                }else{
                    System.out.print(space.getColor()+"|");
                }
            }
            System.out.println();
        }
        System.out.print("Answers(for testing): ");
        for (Shapes ans:answer){
            System.out.print(ans.getColor()+" ");
        }
        System.out.println();
    }
}
