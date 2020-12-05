package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** A strategy that always makes the decision to defect.
 * 
 * @author Lauren Moore
 *
 */

public class AlwaysDefect extends Strategy{
	
  /**
    * Constructor for an instance of the strategy, needs no inputs.
  */
  public AlwaysDefect() {
  }

  /**
   * returns the decision made by the strategy for the turn in which it is called.
   * @param lastMove - the last move made by its opponent
   * @param History - all previous moves made by its opponent in this game
   * @return the character d for defect
  */
  @Override
	public char getDecision(char lastMove, ArrayList<Character> opponentHistory, ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    return 'd';
  }
  
  public StringProperty nameProperty() {
	  SimpleStringProperty StrategyName = new SimpleStringProperty();
	  StrategyName.setValue("AlwaysDefect");
	  return StrategyName;
  }
  
  public StringProperty probabilityProperty() {
	  SimpleStringProperty prob = new SimpleStringProperty();
	  prob.setValue("-");
	  return prob;
  }
  
  public StringProperty roundsProperty() {
	  SimpleStringProperty round = new SimpleStringProperty();
	  round.setValue("-");
	  return round;
  }

}