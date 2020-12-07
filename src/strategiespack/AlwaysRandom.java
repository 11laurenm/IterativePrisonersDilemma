package strategiespack;

import java.util.ArrayList;
import java.util.Random;

/** A strategy that always makes a random decision.
 * 
 * @author Lauren Moore
 *
 */

public class AlwaysRandom extends Strategy{

  /**
   * A random number generator used to generate a random boolean.
   */
  Random booleanGenerator = new Random();
    
  /**
   * Constructor for an instance of the strategy, needs no inputs.
  */
  public AlwaysRandom() {
    
  }
  
  /**
   * returns the decision made by the strategy for the turn in which it is called.
   * @param lastMove - the last move made by its opponent
   * @param History - all previous moves made by its opponent in this game
   * @return the character c for cooperate if the random bool is true, otherwise return d
  */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> History, char myLastMove) {
    if (booleanGenerator.nextBoolean()) {
      return 'c';
    }
    return 'd';
  }

}