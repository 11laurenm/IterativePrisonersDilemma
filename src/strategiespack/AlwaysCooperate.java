package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** A strategy that always makes the decision to cooperate.

 *  @author  Lauren Moore - zfac043
 *
 */

public class AlwaysCooperate extends Strategy {

  /**
    * Constructor for an instance of the strategy, needs no inputs.
   */
  public AlwaysCooperate() {
  }

  /** returns the decision made by the strategy for the turn in which it is called.

   * @param lastMove - the last move made by its opponent
   * @param opponentHistory - all previous moves made by its opponent in this game
   * @param myHistory - all previous moves made by the strategy
   * @param myLastMove - last move made by the strategy
   * @param opponentPoints - score of opponent
   * @return the character c for cooperate
  */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
          ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    return 'c';
  }
  
  /**
   * Method that provides the strategy's name.

   * @return the name of the strategy as a SimpleStringProperty
   */
  public StringProperty nameProperty() {
    SimpleStringProperty strategyName = new SimpleStringProperty();
    strategyName.setValue("AlwaysCooperate");
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
  
  /**
   * Method that provides the colour associated with a strategy.

   * @return the value of the colour variable of the strategy as a SimpleStringProperty
   */
  public StringProperty colourProperty() {
    SimpleStringProperty colour = new SimpleStringProperty();
    colour.setValue("Red");
    return colour;
  }
 
}