package strategiespack;

import java.util.ArrayList;

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

}
