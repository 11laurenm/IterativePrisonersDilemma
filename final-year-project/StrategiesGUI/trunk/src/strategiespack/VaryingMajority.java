package strategiespack;

import java.util.ArrayList;

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
		
	}
	
	/**
	 * Returns the decision made by the strategy, which is whatever move the 
	 * opponent has made in the majority of rounds analysed.
	 * @param lastMove - the last move made by its opponent
	 * @param History - all previous moves made by its opponent in this game
	 * @return the strategy's move for the current round
	 */
	@Override
	public char getDecision(char lastMove, ArrayList<Character> History, char myLastMove) {
		int size = History.size();
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
		
		for(int i = 0; i<iterations; i++) {
			if(History.get(size - i) == 'd') {
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

}
