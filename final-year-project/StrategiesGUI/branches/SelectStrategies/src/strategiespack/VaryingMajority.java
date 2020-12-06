package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A strategy that returns the decision made the majority of times by its opponent.
 * The amount of previous rounds it considers can be decided using the GUI
 * @author Lauren Moore - zfac043
 */
public class VaryingMajority extends Strategy {

  /**
   * The number of previous rounds it considers when deciding its move.
   */
  int rounds;

  /**
   * A constructor that takes a parameter rounds, which is how many of its 
   * opponent's previous moves it considers.
   */
  public VaryingMajority(int rounds) {
    this.rounds = rounds;
  }

  /**
   * Returns the decision made by the strategy, which is whatever move the 
   * opponent has made in the majority of rounds analysed.
   * @param lastMove - the last move made by its opponent
   * @param myHistory - all previous moves made by the strategy
   * @param myLastMove - last move made by the strategy
   * @param opponentPoints - score of opponent
   * @return the strategy's move for the current round
   */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
          ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    int size = opponentHistory.size();
    int iterations;
    int defects = 0;
    int coops = 0;

    if (size == 0) {
      return 'c';
    } else if (size < rounds) {
      iterations = size;
    } else {
      iterations = rounds;
    }

    for (int i = 1; i < iterations + 1; i++) {
      int getIndex = size - i;
      if (opponentHistory.get(getIndex) == 'd') {
        defects++;
      } else {
        coops++;
      }
    }

    if (coops > defects) {
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
    strategyName.setValue("VaryingMajority");
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
    SimpleStringProperty roundsProp = new SimpleStringProperty();
    roundsProp.setValue(Integer.toString(rounds));
    return roundsProp;
  }
  
  /**
   * Sets the value of the variable rounds to the value of round, 
   *      which is provided by the user through the GUI.
   */
  @Override
  public void setRounds(int round) {
    this.rounds = round;
  }

}
