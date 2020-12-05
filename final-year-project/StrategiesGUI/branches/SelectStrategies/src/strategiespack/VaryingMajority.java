package strategiespack;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A strategy that returns the decision made the majority of times by its opponent.
 * The amount of previous rounds it considers can be decided upon construction
 */
public class VaryingMajority extends Strategy{
	
	/**
	 * The number of previous rounds it considers when deciding its move.
	 */
	int rounds;
	
	/**
	 * A constructor that takes a parameter rounds, which is how many of its 
	 * opponent's previous moves it considers.
	 */
	public VaryingMajority(int rounds) {
		this.rounds = rounds;
	}
	
	/**
	 * Returns the decision made by the strategy, which is whatever move the 
	 * opponent has made in the majority of rounds analysed.
	 * @param lastMove - the last move made by its opponent
	 * @param History - all previous moves made by its opponent in this game
	 * @return the strategy's move for the current round
	 */
	@Override
	public char getDecision(char lastMove, ArrayList<Character> opponentHistory, ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
		int size = opponentHistory.size();
		int iterations;
		int defects = 0;
		int coops = 0;
		
		if(size == 0) {
			return 'c';
		} else if(size < rounds) {
			iterations = size;
		} else {
			iterations = rounds;
		}
		
		for(int i = 1; i<iterations + 1; i++) {
			int getIndex = size - i;
			if(opponentHistory.get(getIndex) == 'd') {
				defects++;
			} else {
				coops++;
			}
		}
		
		if(coops > defects) {
			return 'c';
		}
		return 'd';
		
		
	}
	
	public StringProperty nameProperty() {
		  SimpleStringProperty StrategyName = new SimpleStringProperty();
		  StrategyName.setValue("VaryingMajority");
		  return StrategyName;
	  }
	  
	public StringProperty probabilityProperty() {
		  SimpleStringProperty prob = new SimpleStringProperty();
		  prob.setValue("-");
		  return prob;
	}
	  
	public StringProperty roundsProperty() {
		  SimpleStringProperty round = new SimpleStringProperty();
		  round.setValue(Integer.toString(rounds));
		  return round;
	}
	
	@Override
	public void setRounds(int round) {
		this.rounds = round;
	}

}
