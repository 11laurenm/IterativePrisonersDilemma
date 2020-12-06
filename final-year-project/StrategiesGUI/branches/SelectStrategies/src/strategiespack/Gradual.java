package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** A strategy that defects equal to its opponent's total defections 
 * when it is defected against.
 * 
 * @author Lauren Moore -zfac043
 *
 */

public class Gradual extends Strategy {

  /**
   * Used by the strategy to keep track of how many times it should defect in a row.
   */
  int defectsLeft;
  
  /**
   * Used by the strategy to keep track of how many times it should cooperate in a row.
   */
  int cooperatesLeft;
  
  /**
   * Constructor for an instance of the strategy, needs no inputs but sets the value of
   * defectsLeft and cooperatesLeft to 0.
   */
  public Gradual() {
    defectsLeft = 0;
    cooperatesLeft = 0;
  }
  
  /**
   * returns the decision made by the strategy for the turn in which it is called.
   * @param lastMove - the last move made by its opponent
   * @param opponentHistory - all previous moves made by its opponent in this game
   * @param myHistory - all previous moves made by the strategy
   * @param myLastMove - last move made by the strategy
   * @param opponentPoints - score of opponent
   * @return c if opponent hasn't defected, d the same amount of times the opponent
   *     has defected
   */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
          ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
      
    if (myHistory.size() == 0) {
      defectsLeft = 0;
      cooperatesLeft = 0;
      return 'c';
    }
    if (defectsLeft > 0) {
      if (defectsLeft == 1) {
        defectsLeft--;
        cooperatesLeft = 2;
        return 'd';
      }
      defectsLeft--;
      return 'd';
    }
    if (cooperatesLeft > 0) {
      cooperatesLeft--;
      return 'c';
    }
    if (lastMove == 'd') {
      for (char move : opponentHistory) {
        if (move == 'd') {
          defectsLeft++;
        }
      }
      if (defectsLeft == 1) {
        cooperatesLeft = 2;
      }
      defectsLeft--;
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
    strategyName.setValue("Gradual");
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