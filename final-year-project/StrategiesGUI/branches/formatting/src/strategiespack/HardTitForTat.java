package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** A strategy that cooperates on the first two moves, 
 * then defects if its opponent defected on one or more of the two previous moves.

 * @author Lauren Moore -zfac043
 *
 */

public class HardTitForTat extends Strategy {
    
  /**
   * Constructor for an instance of the strategy, needs no inputs.
  */
  public HardTitForTat() {
  }

  /**
   * returns the decision made by the strategy for the turn in which it is called.

   * @param lastMove - the last move made by its opponent
   * @param opponentHistory - all previous moves made by its opponent in this game
   * @param myHistory - all previous moves made by the strategy
   * @param myLastMove - last move made by the strategy
   * @param opponentPoints - score of opponent
   * @return c on first two moves, then d if opponent defected on one or both of previous moves
  */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
          ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    if (myHistory.size() == 0 || myHistory.size() == 1) { //cooperate on the first two moves
      return 'c';
    }
    char secondToLastMove = opponentHistory.get(opponentHistory.size() - 2);
    if (lastMove == 'd' || secondToLastMove == 'd') {
      return 'd'; //defect if opponent defected on either of previous two moves
    }
    return 'c';
  }

  /**
   * Method that provides the strategy's name.

   * @return the name of the strategy as a SimpleStringProperty
   */
  public StringProperty nameProperty() {
    SimpleStringProperty strategyName = new SimpleStringProperty();
    strategyName.setValue("HardTitForTat");
    return strategyName;
  }
  
  /**
   * Method that provides the strategy's probability (if one exists).

   * @return the value of the probability variable of the strategy as a SimpleStringProperty
   */
  public StringProperty probabilityProperty() {
    SimpleStringProperty prob = new SimpleStringProperty();
    prob.setValue("0");
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
  
  public StringProperty colourProperty() {
    SimpleStringProperty colour = new SimpleStringProperty();
    colour.setValue("Beige");
    return colour;
  }

}