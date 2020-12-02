package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** A strategy that always makes the decision to cooperate.
 * 
 * @author Lauren Moore
 *
 */

public class AlwaysCooperate extends Strategy{
	
  /**
    * Constructor for an instance of the strategy, needs no inputs.
   */
  public AlwaysCooperate() {
  }

  /**
   * returns the decision made by the strategy for the turn in which it is called.
   * @param lastMove - the last move made by its opponent
   * @param History - all previous moves made by its opponent in this game
   * @return the character c for cooperate
  */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> History, char myLastMove) {
    return 'c';
  }
  
  public StringProperty nameProperty() {
	  SimpleStringProperty StrategyName = new SimpleStringProperty();
	  StrategyName.setValue("AlwaysCooperate");
	  return StrategyName;
  }
  
  public StringProperty probabilityProperty() {
	  SimpleStringProperty StrategyName = new SimpleStringProperty();
	  StrategyName.setValue("-");
	  return StrategyName;
  }
  
  public StringProperty roundsProperty() {
	  SimpleStringProperty StrategyName = new SimpleStringProperty();
	  StrategyName.setValue("-");
	  return StrategyName;
  }
 
}