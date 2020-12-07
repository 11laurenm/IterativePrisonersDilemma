package strategiespack;

import java.util.ArrayList;

/**
 * The class representing a tournament of the Iterative Prisoner's Dilemma.
 * @author Lauren Moore - zfac043
 *
 */

public class RoundRobin {

  /**
   * All strategies participating in the tournament.
   */
  ArrayList<Strategy> strategies;
  
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

  /**
   * Constructor for the class, which sets the following variables to values 
   * chosen by the user.
   * @param strats - the strategies participating in the tournament
   * @param rounds - the total number of rounds they will play
   * @param payoffs - the scores earned for each combination of decisions
   */
  public RoundRobin(ArrayList<Strategy> strats, int rounds, ArrayList<Integer> payoffs) {
    totalRounds = rounds;
    strategies = strats;
    payoffList = payoffs;
    scores = new ArrayList<>();
    lengths = new ArrayList<>();
  }
        
  /**
   * The method responsible for running the tournament which decides 
   * how many rounds each of the three games should be, then
   * iterates through the list of strategies to run 3 games with every possible pairing.
   */
  public void runTournament() {

    decisions = new ArrayList<ArrayList<Character>>();
    points = new ArrayList<ArrayList<Integer>>();

    VaryGameLength varyLength = new VaryGameLength(totalRounds);
    int first = varyLength.getFirstSet();
    int second = varyLength.getSecondSet();
    int third = varyLength.getThirdSet();

    lengths.add(first); //save the length of each game so GUI can output it
    lengths.add(second);
    lengths.add(third);

    for (int i = 0; i < strategies.size(); i++) {
      for (int j = i; j < strategies.size(); j++) { //iterate through every pair of strategies
        int player1Score = 0; //initialise each player's score for this pairing to 0
        int player2Score = 0;
        Game game1 = new Game(strategies.get(i), strategies.get(j), first, payoffList);
        game1.playGame();
        player1Score = player1Score + game1.getPlayer1Score();
        player2Score = player2Score + game1.getPlayer2Score();
        decisions.add(game1.getAllDecisions()); //add decisions to a master list so GUI can use it
        points.add(game1.getAllScores()); //add points earned to a master list so GUI can use it
        Game game2 = new Game(strategies.get(i), strategies.get(j), second, payoffList);
        game2.playGame();
        player1Score = player1Score + game2.getPlayer1Score();
        player2Score = player2Score + game2.getPlayer2Score();
        decisions.add(game2.getAllDecisions());
        points.add(game2.getAllScores());
        Game game3 = new Game(strategies.get(i), strategies.get(j), third, payoffList);
        game3.playGame();
        player1Score = player1Score + game3.getPlayer1Score();
        player2Score = player2Score + game3.getPlayer2Score();
        decisions.add(game3.getAllDecisions());
        points.add(game3.getAllScores());
        scores.add(player1Score);
        scores.add(player2Score);
      }
    }
  }

  /**
   * Returns the results of the tournament as points are 
   * associated with strategies.
   * @return all strategies in the tournament
   */
  public ArrayList<Strategy> returnResults() {
    return strategies;
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

}