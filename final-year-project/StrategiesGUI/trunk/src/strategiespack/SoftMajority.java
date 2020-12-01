package strategiespack;

import java.util.ArrayList;

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

}
