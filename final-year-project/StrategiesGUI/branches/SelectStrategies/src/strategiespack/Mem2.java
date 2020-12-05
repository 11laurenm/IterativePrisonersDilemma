package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mem2 extends Strategy{
	
	String following = "titForTat";
	
	public Mem2() {
	}
	
	@Override
	public char getDecision(char lastMove, ArrayList<Character> opponentHistory, ArrayList<Character> myHistory, char myLastMove) {
	    if(myHistory.size() == 0) {
	    	following = "titForTat";
	    	return 'c';
	    }
	    if(myHistory.size() % 2 == 0 && following != "alwaysAlwaysDefect") {
	    	if(lastMove == 'c' && opponentHistory.get(opponentHistory.size() - 2) == 'c') {
	    		following = "titForTat";
	    	}
	    	else if(((lastMove == 'c' && myLastMove == 'd') || (lastMove == 'd' && myLastMove == 'c')) && 
	    			((opponentHistory.get(opponentHistory.size() - 2) == 'c' && myHistory.get(myHistory.size() - 2) == 'd') ||
	    			(opponentHistory.get(opponentHistory.size() - 2) == 'd' && myHistory.get(myHistory.size() - 2) == 'c'))) {
	    		following = "titForTwoTats";
	    	}
	    	else {
	    		if(following != "alwaysDefect") {
	    			following = "alwaysDefect";
	    		} else {
	    			following = "alwaysAlwaysDefect";
	    		}
	    	}
	    }
	    
	    if(following == "titForTat") {
	    	return lastMove;
	    }
	    if(following == "titForTwoTats") {
	    	if(lastMove == 'd' && myHistory.get(myHistory.size() - 2) == 'd') {
	    		return 'd';
	    	}
	    	return 'c';
	    }
	    return 'd';
	}
	
	public StringProperty nameProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("Mem2");
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