package strategiespack;

import java.util.ArrayList;

public class RoundRobin {
	
	ArrayList<Strategy> strategies;
	int totalRounds;
	ArrayList<Integer> payoffList;
	ArrayList<Integer> scores;
	public RoundRobin(ArrayList<Strategy> strats, int rounds, ArrayList<Integer> Payoffs) {
		totalRounds = rounds;
		strategies = strats;
		payoffList = Payoffs;
		scores = new ArrayList<>();
	}
	
	public void runTournament() {
		
		VaryGameLength varyLength = new VaryGameLength(totalRounds);
		int first = varyLength.getFirstSet();
		int second = varyLength.getSecondSet();
		int third = varyLength.getThirdSet();
		
		
		for(int i = 0; i < strategies.size(); i++) {
			for(int j = i; j < strategies.size(); j++) {
				int player1Score = 0;
				int player2Score = 0;
				Game game1 = new Game(strategies.get(i), strategies.get(j), first, payoffList);
				game1.playGame();
				player1Score = player1Score + game1.getPlayer1Score();
				player2Score = player2Score + game1.getPlayer2Score();
				Game game2 = new Game(strategies.get(i), strategies.get(j), second, payoffList);
				game2.playGame();
				player1Score = player1Score + game2.getPlayer1Score();
				player2Score = player2Score + game2.getPlayer2Score();
				Game game3 = new Game(strategies.get(i), strategies.get(j), third, payoffList);
				game3.playGame();
				player1Score = player1Score + game3.getPlayer1Score();
				player2Score = player2Score + game3.getPlayer2Score();
				scores.add(player1Score);
				scores.add(player2Score);
			}
		}
	}
	
	public ArrayList<Strategy> returnResults(){
		return strategies;
	}
	
	public ArrayList<Integer> returnScores(){
		return scores;
	}

}

