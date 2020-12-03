package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mistrust extends Strategy{
	
	public Mistrust() {
		
	}
	
	@Override
	public char getDecision(char lastMove, ArrayList<Character> History, char myLastMove) {
	   if(lastMove == 'c') {
		   return 'c';
	   }
	   return 'd';
	}
	
	public StringProperty nameProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("Mistrust");
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
