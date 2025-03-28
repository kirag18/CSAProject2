import java.util.ArrayList;

public class Player {
    private int points;
    private int gamesPlayed;
    private String name;
    private ArrayList<Integer> scores;


    public Player(String name){
        points = 0;
        gamesPlayed = 0;
        this.name = name;
        scores = new ArrayList<>();

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


    public double[] stats(){
          int highScore = 0;
          int average = 0;
          int sum = 0;
          int gamesPlayed = 0;
          // TODO figure out how to track these ;
          int winPercentage =0;
          int numOfWins = 0;
          double[] stats = new double[4];
          for(int i = 0; i < scores.size(); i ++){
              if(scores.get(i) > highScore){
                  highScore = scores.get(i);
              }
              if(scores.get(i) < 10){
                  numOfWins ++;
              }
              winPercentage = numOfWins/ scores.size();
              sum += scores.get(i);
              average = sum / scores.size();
              gamesPlayed ++;
          }
          stats[0] = highScore;
          stats[1] = gamesPlayed;
          stats[2] = average;
          stats[3] = winPercentage;

       return stats;
    }

}
