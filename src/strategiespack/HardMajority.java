package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import strategiespack.Strategy;

/** A strategy that defects if its opponent's defections equal or outnumber its cooperations.
 * 
 * @author Lauren Moore -zfac043
 *
 */

public class HardMajority extends Strategy {

  /**
   * Constructor for an instance of the strategy, needs no inputs.
  */
  public HardMajority() {
  }
  
  /**
   * returns the decision made by the strategy for the turn in which it is called.
   * @param lastMove - the last move made by its opponent
   * @param opponentHistory - all previous moves made by its opponent in this game
   * @param myHistory - all previous moves made by the strategy
   * @param myLastMove - last move made by the strategy
   * @param opponentPoints - score of opponent
   * @return d if opponent's defections equal or outnumber its cooperations
   */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
        ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    int size = opponentHistory.size();
    int defects = 0;
    int coops = 0;
    if (size == 0) {
      return 'd';
    }

    for (int i = 0; i < size; i++) {
      if (opponentHistory.get(i) == 'd') {
        defects++; //counts how many times opponent has defected
      } else {
        coops++; //counts how many times opponent has cooperated
      }
    }

    if (defects >= coops) {
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
    strategyName.setValue("HardMajority");
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