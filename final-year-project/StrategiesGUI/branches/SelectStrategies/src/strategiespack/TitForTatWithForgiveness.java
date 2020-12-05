package strategiespack;

import java.util.ArrayList;
import java.util.Random;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TitForTatWithForgiveness extends Strategy{
	
	double probability;
	Random random = new Random();
	
	public TitForTatWithForgiveness(double prob) {
		probability = prob;
	}
	
	@Override
	public char getDecision(char lastMove, ArrayList<Character> opponentHistory, ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
	    if(lastMove == 'd') {
	    	double randomValue = random.nextDouble();
	    	if(randomValue < probability) {
	        	return 'c';
	        }
	    	return 'd';
	    }
	    return 'c';
	}
	
	public StringProperty nameProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("TitForTatWithForgiveness");
		  return StrategyName;
	}
	  
	public StringProperty probabilityProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue(Double.toString(probability));
		  return StrategyName;
	}
	  
	public StringProperty roundsProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("-");
		  return StrategyName;
	}
	
	@Override
	public void setProbability(double prob) {
			this.probability = prob;
	}

}