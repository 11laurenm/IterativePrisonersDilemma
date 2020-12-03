package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Spiteful extends Strategy{
	
	public Spiteful() {
		
	}
	
	@Override
	public char getDecision(char lastMove, ArrayList<Character> History, char myLastMove) {
		if(History.contains('d')){
			return 'd';
		}
		return 'c';
	}
	
	public StringProperty nameProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("Spiteful");
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
