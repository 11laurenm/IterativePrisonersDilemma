package strategiespack;

import java.util.ArrayList;

/** A class that runs one or more games of the PD.
 * 
 * @author Lauren Moore - zfac043
 *
 */
public class Game {
    
  /**
   * Each strategy has a history - an arraylist of every decision made in this game.
   */
  public ArrayList<Character> historyStrategy1;
  public ArrayList<Character> historyStrategy2;
  
  /**
   * The scores for a particular round.
   */
  public ArrayList<Integer> roundScores;
  
  /**
   * Each strategy is a strategy participating in the game.
   */
  Strategy strategy1;
  Strategy strategy2;
  
  /**
   * Each strategy has a move - the move selected for this round.
   */
  char strategy1move;
  char strategy2move;
  
  /**
   * length is the length of the game i.e. the number 
   * of rounds to be played
   */
  int length;
  
  /**
   * Whether or not a copy (dummy strat) is participating.
   */
  boolean dummyStrat;
  
  /**
   * The scores earned for each combination of decisions.
   */
  ArrayList<Integer> payoffs;
  
  /**
   * Each strategy has an initial score - its total score before this game.
   */
  int player1InitialScore = 0;
  int player2InitialScore = 0;
  
  /**
   * All decisions made in the game.
   */
  ArrayList<Character> decisions;
  
  /**
   * All points earned in the game.
   */
  ArrayList<Integer> points;
  
  /**
   * This method is responsible for running a game (made up of multiple rounds) of 
   * the Prisoner's Dilemma.
   * @param strat1 a strategy being played
   * @param strat2 the other strategy being played
   * @param rounds the amount of rounds to be played
   * @param payoffList the scores earned for each combination of decisions
   */
  public Game(Strategy strat1, Strategy strat2, int rounds, ArrayList<Integer> payoffList) {
    historyStrategy1 = new ArrayList<>();
    historyStrategy2 = new ArrayList<>();
    roundScores = new ArrayList<>();
    decisions = new ArrayList<>();
    points = new ArrayList<>();
    strategy1 = strat1;
    strategy2 = strat2;
    length = rounds;
    dummyStrat = false;
    payoffs = payoffList;
    player1InitialScore = strat1.getPoints();
    player2InitialScore = strat2.getPoints();
  }

  /**
   * When given a history, returns the most recent decision made.
   * @param history - an arraylist of decisions made
   * @return the most recent decision in the history
   */
  public char getLastMove(ArrayList<Character> history) {
    if (history.size() == 0) {
      return 'n';
    }
    return history.get(history.size() - 1);
  }

  /**
   * Adds a decision to the history.
   * @param move the decision that was made
   * @param history the history arraylist that the decision should be added to
   */
  public void setLastMove(char move, ArrayList<Character> history) {
    history.add(move);
  }

  /**
   * Method that handles the running of the game.
   * Uses the round number provided on creation to run that many rounds, 
   * also checks if one strategy is a copy/dummy.
   */
  public void playGame() {
    for (int round = 0; round < length; round++) {
      strategy1move = strategy1.getDecision(getLastMove(historyStrategy2), historyStrategy2, 
              historyStrategy1, getLastMove(historyStrategy1), strategy2.getPoints());
      strategy2move = strategy2.getDecision(getLastMove(historyStrategy1), historyStrategy1, 
              historyStrategy2, getLastMove(historyStrategy2), strategy1.getPoints());
        
      decisions.add(strategy1move); //save the move so GUI can output it
      decisions.add(strategy2move);

      setLastMove(strategy1move, historyStrategy1);
      setLastMove(strategy2move, historyStrategy2);
        
      roundScores = calculateScores(strategy1move, strategy2move);
        
      strategy1.addPoints(roundScores.get(0));
      if (!isDummy()) { //if one is a dummy then it should not gain points
        strategy2.addPoints(roundScores.get(1));
      }
        
      points.add(roundScores.get(0)); //save the points earned so GUI can output them
      points.add(roundScores.get(1));
        
      roundScores.clear();
    }
    
  }

  /**
   * Used to calculate the scores of each strategy given their decisions.
   * @param decision1 - decision made by strategy 1
   * @param decision2 - decision made by strategy 2
   * @return an arraylist containing the scores for both strategies
   */
  public ArrayList<Integer> calculateScores(char decision1, char decision2) {
    ArrayList<Integer> scores = new ArrayList();
    if (decision1 == 'c') {
      if (decision2 == 'c') {
        scores.add(payoffs.get(0));
        scores.add(payoffs.get(0));
      } else {
        scores.add(payoffs.get(2));
        scores.add(payoffs.get(1));
      }
    } else {
      if (decision2 == 'c') {
        scores.add(payoffs.get(1));
        scores.add(payoffs.get(2));  
      } else {
        scores.add(payoffs.get(3));
        scores.add(payoffs.get(3));
      } 
    }
    
    return scores;
  }

  /**
   * Method that determines if one strategy is a dummy.
   * @return true if one strategy is a dummy, false if not
   */
  public boolean isDummy() {
    if (strategy1.equals(strategy2)) { //if the strategies are the same one is a dummy
      return true;
    }
    return false;
  }

  /**
   * Calculates the score the player started the game with.
   * @return the score the player started the game with
   */
  public int getPlayer1Score() {
    return strategy1.getPoints() - player1InitialScore;
  }

  /**
   * Calculates the score the player started the game with.
   * @return the score the player started the game with
   */
  public int getPlayer2Score() {
    return strategy2.getPoints() - player2InitialScore;
  }

  /**
   * getter for arraylist of all decisions made in the game.
   * @return arraylist of all decisions made in the game
   */
  public ArrayList<Character> getAllDecisions() {
    return decisions;
  }

  /**
   * getter for arraylist of all points earned in the game.
   * @return arraylist of all points earned in the game
   */
  public ArrayList<Integer> getAllScores() {
    return points;
  }
   
}