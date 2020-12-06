package strategiespack;

import java.util.ArrayList;

public class RoundRobin {

  ArrayList<Strategy> strategies;
  int totalRounds;
  ArrayList<Integer> payoffList;
  ArrayList<Integer> scores;
  ArrayList<ArrayList<Character>> decisions;
  ArrayList<ArrayList<Integer>> points;
  ArrayList<Integer> lengths;
  ArrayList<String> names;

  public RoundRobin(ArrayList<Strategy> strats, int rounds, ArrayList<Integer> Payoffs) {
    totalRounds = rounds;
    strategies = strats;
    payoffList = Payoffs;
    scores = new ArrayList<>();
    lengths = new ArrayList<>();
  }
        

  public void runTournament() {
	
    decisions = new ArrayList<ArrayList<Character>>();
    points = new ArrayList<ArrayList<Integer>>();
	
    VaryGameLength varyLength = new VaryGameLength(totalRounds);
    int first = varyLength.getFirstSet();
    int second = varyLength.getSecondSet();
    int third = varyLength.getThirdSet();

    lengths.add(first);
    lengths.add(second);
    lengths.add(third);

    for (int i = 0; i < strategies.size(); i++) {
      for (int j = i; j < strategies.size(); j++) {
        int player1Score = 0;
        int player2Score = 0;
        Game game1 = new Game(strategies.get(i), strategies.get(j), first, payoffList);
        game1.playGame();
        player1Score = player1Score + game1.getPlayer1Score();
        player2Score = player2Score + game1.getPlayer2Score();
        decisions.add(game1.getAllDecisions());
        points.add(game1.getAllScores());
        Game game2 = new Game(strategies.get(i), strategies.get(j), second, payoffList);
        game2.playGame();
        player1Score = player1Score + game2.getPlayer1Score();
        player2Score = player2Score + game2.getPlayer2Score();
        decisions.add(game2.getAllDecisions());
        points.add(game2.getAllScores());
        Game game3 = new Game(strategies.get(i), strategies.get(j), third, payoffList);
        game3.playGame();
        player1Score = player1Score + game3.getPlayer1Score();
        player2Score = player2Score + game3.getPlayer2Score();
        decisions.add(game3.getAllDecisions());
        points.add(game3.getAllScores());
        scores.add(player1Score);
        scores.add(player2Score);
      }
    }
  }

  public ArrayList<Strategy> returnResults() {
    return strategies;
  }

  public ArrayList<Integer> returnScores() {
    return scores;
  }

  public ArrayList<ArrayList<Character>> returnDecisions() {
    return decisions;
  }

  public ArrayList<ArrayList<Integer>> returnPoints() {
    return points;
  }

  public ArrayList<Integer> returnGameLengths() {
    return lengths;
  }

}