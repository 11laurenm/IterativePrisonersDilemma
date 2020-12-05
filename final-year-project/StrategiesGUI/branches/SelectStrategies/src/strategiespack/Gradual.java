package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Gradual extends Strategy{
	
	int defectsLeft;
	int cooperatesLeft;
	
	public Gradual() {
		defectsLeft = 0;
		cooperatesLeft = 0;
	}
	
	@Override
	public char getDecision(char lastMove, ArrayList<Character> opponentHistory, ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
	    if(myHistory.size() == 0) {
	    	defectsLeft = 0;
	    	cooperatesLeft = 0;
	    	return 'c';
	    }
	    if (defectsLeft > 0) {
	    	if(defectsLeft == 1) {
	    		defectsLeft--;
	    		cooperatesLeft = 2;
	    		return 'd';
	    	}
	    	defectsLeft--;
	    	return 'd';
	    }
	    if (cooperatesLeft > 0) {
	    	cooperatesLeft--;
	    	return 'c';
	    }
	    if(lastMove == 'd') {
	    	for(char move : opponentHistory) {
	    		if(move == 'd') {
	    			defectsLeft++;
	    		}
	    	}
	    	if(defectsLeft == 1) {
	    		cooperatesLeft = 2;
	    	}
	    	defectsLeft--;
	    	return 'd';
	    }
	    return 'c';
	}
	
	public StringProperty nameProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("Gradual");
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