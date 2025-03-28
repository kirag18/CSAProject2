import java.util.ArrayList;

public class Player {
    private int points;
    private int gamesPlayed;
    private String name;

    public Player(String name){
        points = 0;
        gamesPlayed = 0;
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints(){
        return points;
    }

    public int getGamesPlayed(){
        return gamesPlayed;
    }

    public String getName() {
        return name;
    }
}
