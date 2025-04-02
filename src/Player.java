import java.util.ArrayList;

public class Player {
    private int gamesPlayed;
    private ArrayList<Integer> scores;


    public Player(){
        gamesPlayed = 0;
        scores = new ArrayList<>();

    }

    public int getGamesPlayed(){
        return gamesPlayed;
    }


    public void addScore(int score){
        scores.add(score);
        gamesPlayed++;
    }


    public double[] stats(){
          if (gamesPlayed == 0){
              return new double[4];
          }
          int highScore = 0;
          int sum = 0;
          // TODO figure out how to track these ;
          int numOfWins = 0;
          double[] stats = new double[4];
          for(int i = 0; i < scores.size(); i ++){
              if(scores.get(i) > highScore){
                  highScore = scores.get(i);
              }
              if(scores.get(i) >=0){
                  numOfWins ++;
              }

              sum += scores.get(i);

          }
          double winPercentage = numOfWins*100.0/ scores.size();
          winPercentage = Math.round(winPercentage*100)/100.0;
          double average = (double) sum / scores.size();
          average = Math.round(average*100)/100.0;
          stats[0] = highScore;
          stats[1] = average;
          stats[2] = winPercentage;
          stats[3] = sum;

          return stats;
    }

}
