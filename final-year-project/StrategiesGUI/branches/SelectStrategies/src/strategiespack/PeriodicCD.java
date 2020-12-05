package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PeriodicCD extends Strategy{
	
	public PeriodicCD() {
	}
	
	@Override
	public char getDecision(char lastMove, ArrayList<Character> opponentHistory, ArrayList<Character> myHistory, char myLastMove) {
	    if(myLastMove == 'c') {
	    	return 'd';
	    }
	    return 'c';
	}
	
	public StringProperty nameProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("PeriodicCD");
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