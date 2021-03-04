package strategiespack;

import java.util.ArrayList;

public class Tournament {
  
  /**
   * The total number of rounds each pairing will play.
   */
  int totalRounds;
  
  /**
   * The scores earned for each combination of decisions.
   */
  ArrayList<Integer> payoffList;
  
  /**
   * The total points earned in a game by each strategy.
   */
  ArrayList<Integer> scores;
  
  /**
   * All decisions made in the tournament.
   */
  ArrayList<ArrayList<Character>> decisions;
  
  /**
   * All points earned in the tournament.
   */
  ArrayList<ArrayList<Integer>> points;
  
  /**
   * length is the length of the game i.e. the number 
   * of rounds to be played
   */
  ArrayList<Integer> lengths;
  
  ArrayList<Integer> setGameLengths;
  
  int first;
  
  int second;
  
  int third;
  
  int player1Score;
  
  int player2Score;
  
  public Tournament() {
    
    player1Score = 0;
    player2Score = 0;
    
  }

  /**
   * getter for scores, the total points earned in a game by each strategy.
   * @return scores
   */
  public ArrayList<Integer> returnScores() {
    return scores;
  }

  /**
   * getter for decisions, all decisions made in the tournament.
   * @return decisions
   */
  public ArrayList<ArrayList<Character>> returnDecisions() {
    return decisions;
  }

  /**
   * getter for points, all points earned in the tournament.
   * @return points
   */
  public ArrayList<ArrayList<Integer>> returnPoints() {
    return points;
  }

  /**
   * getter for lengths, length is the length of the game i.e. the number 
   * of rounds to be played
   * @return
   */
  public ArrayList<Integer> returnGameLengths() {
    return lengths;
  }
  
  public void endOfGame(Game game) {
    player1Score = player1Score + game.getPlayer1Score();
    player2Score = player2Score + game.getPlayer2Score();
    decisions.add(game.getAllDecisions()); //add decisions to a master list so GUI can use it
    points.add(game.getAllScores()); //add points earned to a master list so GUI can use it
  }

}
