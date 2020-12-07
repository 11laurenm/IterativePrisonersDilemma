package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** A strategy that cooperates on the first two moves, 
 * then defects if its opponent defected on both of the two previous moves.
 * 
 * @author Lauren Moore -zfac043
 *
 */

public class TitForTwoTats extends Strategy {

  /**
   * Constructor for an instance of the strategy, needs no inputs.
  */  
  public TitForTwoTats() {
  }

  /**
   * returns the decision made by the strategy for the turn in which it is called.
   * @param lastMove - the last move made by its opponent
   * @param opponentHistory - all previous moves made by its opponent in this game
   * @param myHistory - all previous moves made by the strategy
   * @param myLastMove - last move made by the strategy
   * @param opponentPoints - score of opponent
   * @return c on first two moves, then d if opponent defected on both of previous two moves
  */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
        ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    if (myHistory.size() == 0 || myHistory.size() == 1) { //play c on first two turns
      return 'c';
    }
    char secondToLastMove = opponentHistory.get(opponentHistory.size() - 2);
    if (lastMove == 'd' && secondToLastMove == 'd') { //if opponent defected on previous two turns
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
    strategyName.setValue("TitForTwoTats");
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