package strategiespack;

import java.util.ArrayList;

/**
 * The class representing a tournament of the Iterative Prisoner's Dilemma.
 * @author Lauren Moore - zfac043
 *
 */

public class RoundRobin extends Tournament{

  /**
   * All strategies participating in the tournament.
   */
  ArrayList<Strategy> strategies;
  
  /**
   * Constructor for the class, which sets the following variables to values 
   * chosen by the user.
   * @param strats - the strategies participating in the tournament
   * @param rounds - the total number of rounds they will play
   * @param payoffs - the scores earned for each combination of decisions
   */
  public RoundRobin(ArrayList<Strategy> strats, int rounds, ArrayList<Integer> payoffs, 
      ArrayList<Integer> gameLengths) {
    totalRounds = rounds;
    strategies = strats;
    payoffList = payoffs;
    scores = new ArrayList<>();
    lengths = new ArrayList<>();
    setGameLengths = gameLengths;
  }
        
  /**
   * The method responsible for running the tournament which decides 
   * how many rounds each of the three games should be, then
   * iterates through the list of strategies to run 3 games with every possible pairing.
   */
  public void runTournament() {

    decisions = new ArrayList<ArrayList<Character>>();
    points = new ArrayList<ArrayList<Integer>>();

    if(setGameLengths.size() == 0) {
      VaryGameLength varyLength = new VaryGameLength(totalRounds);
      first = varyLength.getFirstSet();
      second = varyLength.getSecondSet();
      third = varyLength.getThirdSet();
    } else {
      first = setGameLengths.get(0);
      second = setGameLengths.get(1);
      third = setGameLengths.get(2);
    }

    lengths.add(first); //save the length of each game so GUI can output it
    lengths.add(second);
    lengths.add(third);

    for (int i = 0; i < strategies.size(); i++) {
      for (int j = i; j < strategies.size(); j++) { //iterate through every pair of strategies
        player1Score = 0; //initialise each player's score for this pairing to 0
        player2Score = 0;
        Game game1 = new Game(strategies.get(i), strategies.get(j), first, payoffList);
        game1.playGame();
        endOfGame(game1);
        Game game2 = new Game(strategies.get(i), strategies.get(j), second, payoffList);
        game2.playGame();
        endOfGame(game2);
        Game game3 = new Game(strategies.get(i), strategies.get(j), third, payoffList);
        game3.playGame();
        endOfGame(game3);
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

}