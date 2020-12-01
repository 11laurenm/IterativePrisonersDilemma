package strategiespack;

import java.util.ArrayList;

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

}
