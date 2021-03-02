package strategiespack;

import java.util.ArrayList;

public class Evolutionary extends Tournament{
  
  ArrayList<Node> nodes;
  ArrayList<Integer> genScores;
  int generations;
  
  public Evolutionary(ArrayList<Node> nodeList, int rounds, ArrayList<Integer> payoffs, 
      ArrayList<Integer> gameLengths, int gens){
    totalRounds = rounds;
    nodes = nodeList;
    genScores = new ArrayList<Integer>(nodes.size());
    setGameLengths = gameLengths;
    payoffList = payoffs;
    generations = gens;
  }
  
  public void setUpTournament() {
    
    decisions = new ArrayList<ArrayList<Character>>();
    points = new ArrayList<ArrayList<Integer>>();
    
    if(setGameLengths.size() == 0) {
      VaryGameLength varyLength = new VaryGameLength(totalRounds);
      first = varyLength.getFirstSet();
      second = varyLength.getSecondSet();
      third = varyLength.getThirdSet();
    } else {
      first = setGameLengths.get(0);
      second = setGameLengths.get(1);
      third = setGameLengths.get(2);
    }
  }
  
  public void runGeneration() {
    for(int nodeNumber = 0; nodeNumber < nodes.size(); nodeNumber++) {
      Node currentNode = nodes.get(nodeNumber);
      currentNode.strategy.points = 0;
      currentNode.setPlayedAllGames(false);
    }
    
    for(int nodeNumber = 0; nodeNumber < nodes.size(); nodeNumber++) {
      Node currentNode = nodes.get(nodeNumber);
      for(int neighbourNumber = 0; neighbourNumber < currentNode.getNeighbours().size(); neighbourNumber++) {
        Node neighbourNode = currentNode.neighbours.get(neighbourNumber);
        player1Score = 0; //initialise each player's score for this pairing to 0
        player2Score = 0;
        if(!neighbourNode.getPlayedAllGames()) {
          Game game1 = new Game(currentNode.getStrategy(), neighbourNode.getStrategy(), first, payoffList);
          game1.playGame();
          endOfGame(game1);
          Game game2 = new Game(currentNode.getStrategy(), neighbourNode.getStrategy(), second, payoffList);
          game2.playGame();
          endOfGame(game2);
          Game game3 = new Game(currentNode.getStrategy(), neighbourNode.getStrategy(), third, payoffList);
          game3.playGame();
          endOfGame(game3);
        }
      }
      nodes.get(nodeNumber).setPlayedAllGames(true);
    }
    normaliseScores();
    setGenerationScores();
    updateNodes();
  }
  
  public void updateNodes() {
    for(Node n: nodes) {
      Node highestScoring = n;
      for(Node neighbour: n.neighbours) {
        if(neighbour.getStrategy().getPoints() > highestScoring.getStrategy().getPoints()) {
          highestScoring = neighbour;
        }
      }
      try {
        Strategy strat = highestScoring.getStrategy().getClass().newInstance();
        strat.setProbability(Double.parseDouble(highestScoring.getStrategy().probabilityProperty().get()));
        strat.setRounds(Integer.parseInt(highestScoring.getStrategy().roundsProperty().get()));
        n.newStrategy = strat;
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    for(Node n: nodes) {
      n.strategy = n.newStrategy;
    }
  }
  
  public ArrayList<Node> returnResults() {
    return nodes;
  }
  
  public void setGenerationScores() {
    int score;
    genScores.clear();
    for(int n = 0; n < nodes.size(); n++) {
      score = nodes.get(n).getStrategy().getPoints();
      genScores.add(score);
    }
  }
  
  public ArrayList<Integer> returnGenerationScores() {
    return genScores;
  }
  
  public void normaliseScores() {
    for(Node n: nodes) {
      int neighboursSize = n.getNeighbours().size();
      n.getStrategy().setPoints(n.getStrategy().getPoints()/neighboursSize);
    }
  }
  
  

}
