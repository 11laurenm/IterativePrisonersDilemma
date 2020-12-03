package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import strategiespack.Strategy;

public class SoftMajority extends Strategy{
	
	public SoftMajority() {
		
	}
	
	@Override
	public char getDecision(char lastMove, ArrayList<Character> History, char myLastMove) {
		int size = History.size();
		int defects = 0;
		int coops = 0;
		
		if(size == 0) {
			return 'c';
		}
		
		for(int i = 0; i<size; i++) {
			if(History.get(i) == 'd') {
				defects++;
			} else {
				coops++;
			}
		}
		
		if(coops >= defects) {
			return 'c';
		}
		return 'd';
		
		
	}
	
	public StringProperty nameProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("SoftMajority");
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
