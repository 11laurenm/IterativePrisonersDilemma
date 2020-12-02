package strategiespack;

import java.util.ArrayList;

public class RoundRobin {
	
	ArrayList<Strategy> strategies;
	int totalRounds;
	ArrayList<Integer> payoffList;
	public RoundRobin(ArrayList<Strategy> strats, int rounds, ArrayList<Integer> Payoffs) {
		totalRounds = rounds;
		strategies = strats;
		payoffList = Payoffs;
	}
	
	public void runTournament() {
		VaryGameLength varyLength = new VaryGameLength(totalRounds);
		int first = varyLength.getFirstSet();
		int second = varyLength.getSecondSet();
		int third = varyLength.getThirdSet();
		for(int i = 0; i < strategies.size(); i++) {
			for(int j = i; j < strategies.size(); j++) {
				Game game1 = new Game(strategies.get(i), strategies.get(j), first, payoffList);
				game1.playGame();
				Game game2 = new Game(strategies.get(i), strategies.get(j), second, payoffList);
				game2.playGame();
				Game game3 = new Game(strategies.get(i), strategies.get(j), third, payoffList);
				game3.playGame();
			}
		}
	}

}

