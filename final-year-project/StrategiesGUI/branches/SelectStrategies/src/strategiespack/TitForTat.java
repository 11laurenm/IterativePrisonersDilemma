package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A strategy that begins by cooperating, then copies its opponent's previous move.
 * @author Lauren Moore - zfac043
 *
 */
public class TitForTat extends Strategy {

  /**
  * Constructor for an instance of the strategy, needs no inputs.
  */
  public TitForTat() {
  }

  /**
   * Returns the opponent's previous move, or c if no previous move.
   * @param lastMove - the last move made by its opponent
   * @param myHistory - all previous moves made by the strategy
   * @param myLastMove - last move made by the strategy
   * @param opponentPoints - score of opponent
   * @return the strategy's move for the current round
   */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
        ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    if (lastMove == 'd') {
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
    strategyName.setValue("TitForTat");
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
