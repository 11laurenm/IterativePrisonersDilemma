package strategiespack;

import java.util.ArrayList;
import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** A strategy that always makes a random decision.
 * 
 * @author Lauren Moore -zfac043
 *
 */

public class AlwaysRandom extends Strategy {

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
   * @param myHistory - all previous moves made by the strategy
   * @param myLastMove - last move made by the strategy
   * @param opponentPoints - score of opponent
   * @return the character c for cooperate if the random bool is true, otherwise return d
  */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
          ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    if (booleanGenerator.nextBoolean()) {
      return 'c';
    }
    return 'd';
  }
  
  /**
   * Method that provides the strategy's name.
   * @return the name of the strategy as a SimpleStringProperty
   */
  public StringProperty nameProperty() {
    SimpleStringProperty strategyName = new SimpleStringProperty();
    strategyName.setValue("AlwaysRandom");
    return strategyName;
  }
  
  /**
   * Method that provides the strategy's probability (if one exists).
   * @return the value of the probability variable of the strategy as a SimpleStringProperty
   */
  public StringProperty probabilityProperty() {
    SimpleStringProperty prob = new SimpleStringProperty();
    prob.setValue("-");
    return prob;
  }
  
  /**
   * Method that provides the number of rounds the strategy considers (if one exists).
   * @return the value of the rounds variable of the strategy as a SimpleStringProperty
   */
  public StringProperty roundsProperty() {
    SimpleStringProperty rounds = new SimpleStringProperty();
    rounds.setValue("-");
    return rounds;
  }
}