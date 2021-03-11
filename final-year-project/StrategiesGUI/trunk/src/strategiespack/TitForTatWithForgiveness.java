package strategiespack;

import java.util.ArrayList;
import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**  A strategy similar to tit for tat in that it begins by cooperating then 
 * copies its opponent’s previous move in every subsequent round.  
 * However, it has a random chance (normally quite low) 
 * of cooperating even if its opponent defected on the previous round

 * @author Lauren Moore -zfac043
 *
 */

public class TitForTatWithForgiveness extends Strategy {

  /**
   * The probability that the strategy will cooperate when its opponent's 
   *      previous move was defect.
   */
  double probability;
  /**
   * Random number generator used during decision making.
   */
  Random random = new Random();

  public TitForTatWithForgiveness() {
  }
  
  /**
   * Constructor that sets the probability variable to value of parameter prob.

   * @param prob The probability that the strategy will cooperate when its opponent's 
   *      previous move was defect.
   */
  public TitForTatWithForgiveness(double prob) {
    probability = prob;
  }

  /**
   * returns the decision made by the strategy for the turn in which it is called.

   * @param lastMove - the last move made by its opponent
   * @param opponentHistory - all previous moves made by its opponent in this game
   * @param myHistory - all previous moves made by the strategy
   * @param myLastMove - last move made by the strategy
   * @param opponentPoints - score of opponent
   * @return c initially, then opponents last move, with a random chance to return c instead of d
  */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
        ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    if (lastMove == 'd') {
      double randomValue = random.nextDouble();
      if (randomValue < probability) { //random chance of cooperating when opponent defected
        return 'c';
      }
      return 'd';
    }
    return 'c';
  }
  
  /**
   * Method that provides the strategy's name.

   * @return the name of the strategy as a SimpleStringProperty
   */
  public StringProperty nameProperty() {
    SimpleStringProperty strategyName = new SimpleStringProperty();
    strategyName.setValue("TitForTatWithForgiveness");
    return strategyName;
  }
  
  /**
   * Method that provides the strategy's probability (if one exists).

   * @return the value of the probability variable of the strategy as a SimpleStringProperty
   */
  public StringProperty probabilityProperty() {
    SimpleStringProperty prob = new SimpleStringProperty();
    prob.setValue(Double.toString(probability));
    return prob;
  }
  
  /**
   * Method that provides the number of rounds the strategy considers (if one exists).

   * @return the value of the rounds variable of the strategy as a SimpleStringProperty
   */
  public StringProperty roundsProperty() {
    SimpleStringProperty rounds = new SimpleStringProperty();
    rounds.setValue("0");
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
  
  /**
   * Method that provides the colour associated with a strategy.

   * @return the value of the colour variable of the strategy as a SimpleStringProperty
   */
  public StringProperty colourProperty() {
    SimpleStringProperty colour = new SimpleStringProperty();
    colour.setValue("Lime");
    return colour;
  }
  
}