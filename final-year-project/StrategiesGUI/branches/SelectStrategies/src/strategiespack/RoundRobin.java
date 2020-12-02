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
		System.out.println("CREATED VGL");
		int first = varyLength.getFirstSet();
		System.out.println("CREATED First");
		int second = varyLength.getSecondSet();
		System.out.println("CREATED second");
		int third = varyLength.getThirdSet();
		System.out.println("CREATED third");
		
		
		for(int i = 0; i < strategies.size(); i++) {
			for(int j = i; j < strategies.size(); j++) {
				Game game1 = new Game(strategies.get(i), strategies.get(j), first, payoffList);
				System.out.println("CREATED game1");
				game1.playGame();
				System.out.println("run game1");
				Game game2 = new Game(strategies.get(i), strategies.get(j), second, payoffList);
				game2.playGame();
				Game game3 = new Game(strategies.get(i), strategies.get(j), third, payoffList);
				game3.playGame();
			}
		}
		System.out.println("Tournament concluded");
	}

}

