package strategiespack;

import java.util.ArrayList;
import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
      if (randomValue < probability) {
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