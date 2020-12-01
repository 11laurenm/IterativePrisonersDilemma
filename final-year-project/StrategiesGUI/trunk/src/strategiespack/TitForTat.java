package strategiespack;

import java.util.ArrayList;

/**
 * A strategy that begins by cooperating, then copies its opponent's previous move.
 * @author Lauren Moore
 *
 */
public class TitForTat extends Strategy{
	
	/**
	 * Constructor for an instance of the strategy, needs no inputs.
	*/
	public TitForTat() {
	      
	}
	
	/**
	 * Returns the opponent's previous move, or c if no previous move.
	 * @param lastMove - the last move made by its opponent
	 * @param History - all previous moves made by its opponent in this game
	 * @return the strategy's move for the current round
	 */
	@Override
	public char getDecision(char lastMove, ArrayList<Character> History, char myLastMove) {
	   if(lastMove == 'd') {
		   return 'd';
	   }
	   return 'c';
	}

}
