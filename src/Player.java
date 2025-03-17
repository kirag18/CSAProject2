public class Player {
    private int points;
    private int gamesPlayed;

    public Player(){
        points = 0;
        gamesPlayed = 0;
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

}
