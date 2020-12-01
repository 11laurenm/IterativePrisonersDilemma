package strategiespack;

import java.util.ArrayList;

public class RoundRobin {
	
	ArrayList<Strategy> strategies;
	int totalRounds;
	public RoundRobin(ArrayList<Strategy> strats, int rounds) {
		totalRounds = rounds;
		strategies = strats;
	}
	
	public void runTournament() {
		VaryGameLength varyLength = new VaryGameLength(totalRounds);
		int first = varyLength.getFirstSet();
		int second = varyLength.getSecondSet();
		int third = varyLength.getThirdSet();
		for(int i = 0; i < strategies.size(); i++) {
			for(int j = i; j < strategies.size(); j++) {
				Game game1 = new Game(strategies.get(i), strategies.get(j), first);
				game1.playGame();
				Game game2 = new Game(strategies.get(i), strategies.get(j), second);
				game2.playGame();
				Game game3 = new Game(strategies.get(i), strategies.get(j), third);
				game3.playGame();
			}
		}
	}

}

