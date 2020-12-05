package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prober extends Strategy{
	
	public Prober() {
	}
	
	@Override
	public char getDecision(char lastMove, ArrayList<Character> opponentHistory, ArrayList<Character> myHistory, char myLastMove) {
	    if(myHistory.size() == 0) {
	    	return 'd';
	    }
	    if(myHistory.size() == 1 || myHistory.size() == 2) {
	    	return 'c';
	    }
	    if(opponentHistory.get(1) == 'c' && opponentHistory.get(2) == 'c') {
	    	return 'd';
	    }
	    if(lastMove == 'c') {
	    	return 'c';
	    }
	    return 'd';
	}
	
	public StringProperty nameProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("Prober");
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