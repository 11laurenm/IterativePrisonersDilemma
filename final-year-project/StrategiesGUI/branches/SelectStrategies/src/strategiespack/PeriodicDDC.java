package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PeriodicDDC extends Strategy{
	
	public PeriodicDDC() {
	}
	
	@Override
	public char getDecision(char lastMove, ArrayList<Character> History, char myLastMove) {
		if(History.size() == 0 || History.size() == 1) {
			return 'd';
		}
		char secondToLastMove = History.get(History.size() - 2);
	    if(myLastMove == 'd' && secondToLastMove == 'd') {
	    	return 'c';
	    }
	    return 'd';
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
