package strategiespack;

import java.util.ArrayList;

public class Pavlov extends Strategy{
	
	public Pavlov() {
		
	}
	
	@Override
	public char getDecision(char lastMove, ArrayList<Character> History, char myLastMove) {
	   if(lastMove == myLastMove) {
		   return 'c';
	   }
	   return 'd';
	}

}
