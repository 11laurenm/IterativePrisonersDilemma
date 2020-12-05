package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A strategy that begins by cooperating, then copies its opponent's previous move.
 * @author Lauren Moore
 *
 */
public class TitForTat extends Strategy{
	
	/**
	 * Constructor for an instance of the strategy, needs no inputs.
	*/
	public TitForTat() {
	      
	}
	
	/**
	 * Returns the opponent's previous move, or c if no previous move.
	 * @param lastMove - the last move made by its opponent
	 * @param History - all previous moves made by its opponent in this game
	 * @return the strategy's move for the current round
	 */
	@Override
	public char getDecision(char lastMove, ArrayList<Character> opponentHistory, ArrayList<Character> myHistory, char myLastMove) {
	   if(lastMove == 'd') {
		   return 'd';
	   }
	   return 'c';
	}
	
	public StringProperty nameProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("TitForTat");
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
