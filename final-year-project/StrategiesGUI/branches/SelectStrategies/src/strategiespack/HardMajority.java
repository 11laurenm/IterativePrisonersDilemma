package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import strategiespack.Strategy;

public class HardMajority extends Strategy{
	
	public HardMajority() {
		
	}
	
	@Override
	public char getDecision(char lastMove, ArrayList<Character> opponentHistory, ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
		int size = opponentHistory.size();
		int defects = 0;
		int coops = 0;
		
		if(size == 0) {
			return 'd';
		}
		
		for(int i = 0; i<size; i++) {
			if(opponentHistory.get(i) == 'd') {
				defects++;
			} else {
				coops++;
			}
		}
		
		if(defects >= coops) {
			return 'd';
		}
		return 'c';
		
		
	}
	
	public StringProperty nameProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("HardMajority");
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