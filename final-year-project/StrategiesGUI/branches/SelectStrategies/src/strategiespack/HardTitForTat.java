package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HardTitForTat extends Strategy{
	
	public HardTitForTat() {
	}
	
	@Override
	public char getDecision(char lastMove, ArrayList<Character> opponentHistory, ArrayList<Character> myHistory, char myLastMove) {
		if(myHistory.size() == 0 || myHistory.size() == 1) {
			return 'c';
		}
		char secondToLastMove = opponentHistory.get(opponentHistory.size() - 2);
		if(lastMove == 'd' || secondToLastMove == 'd') {
			return 'd';
		}
		return 'c';
	}
	
	public StringProperty nameProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("HardTitForTat");
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