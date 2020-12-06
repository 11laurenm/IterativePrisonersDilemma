package strategiespack;

import java.util.ArrayList;
import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TitForTatWithForgiveness extends Strategy {

  double probability;
  Random random = new Random();

  public TitForTatWithForgiveness(double prob) {
    probability = prob;
  }

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
  
  @Override
  public void setProbability(double prob) {
    this.probability = prob;
  }
  
}