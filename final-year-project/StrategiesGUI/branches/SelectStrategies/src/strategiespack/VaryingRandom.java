package strategiespack;

import java.util.ArrayList;
import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A strategy that randomly returns c or d.
 * The probability of cooperating can be decided using the GUI
 * @author Lauren Moore - zfac043
 */

public class VaryingRandom extends Strategy {
  
  /**
   * Random number generator used during decision making.
  */
  Random random = new Random();
  
  /**
   * The probability that the strategy will cooperate.
   */
  double probability;
 
  /**
   * A constructor that takes a parameter prob, which is the probability 
   *      of it cooperating.
   */
  public VaryingRandom(double prob) {
    probability = prob;
  }
  
  /**
   * returns the decision made by the strategy for the turn in which it is called.
   * @param lastMove - the last move made by its opponent
   * @param opponentHistory - all previous moves made by its opponent in this game
   * @param myHistory - all previous moves made by the strategy
   * @param myLastMove - last move made by the strategy
   * @param opponentPoints - score of opponent
   * @return c or d, with probability of returning c set by user
  */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
          ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    double randomValue = random.nextDouble();
    if (randomValue < probability) {
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
    strategyName.setValue("VaryingRandom");
    return strategyName;
  }
  
  /**
   * Method that provides the strategy's probability (if one exists).
   * @return the value of the probability variable of the strategy as a SimpleStringProperty
   */
  @Override
  public StringProperty probabilityProperty() {
    SimpleStringProperty prob = new SimpleStringProperty();
    prob.setValue(Double.toString(probability));
    return prob;
  }
  
  /**
   * Method that provides the number of rounds the strategy considers (if one exists).
   * @return the value of the rounds variable of the strategy as a SimpleStringProperty
   */
  @Override
  public StringProperty roundsProperty() {
    SimpleStringProperty rounds = new SimpleStringProperty();
    rounds.setValue("-");
    return rounds;
  }
  
  /**
   * Sets the value of the variable probability to the value of prob, 
   *      which is provided by the user through the GUI.
   */
  @Override
  public void setProbability(double prob) {
    this.probability = prob;
  }
}