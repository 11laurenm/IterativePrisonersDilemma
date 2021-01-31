package strategiespack;

import java.util.ArrayList;

public class Evolutionary {
  
  ArrayList<Node> nodes;
  int totalRounds;
  ArrayList<Integer> lengths;
  ArrayList<Integer> setGameLengths;
  ArrayList<Integer> payoffList;
  
  int first;
  int second;
  int third;
  
  public Evolutionary(ArrayList<Node> nodeList, int rounds, ArrayList<Integer> payoffs, 
      ArrayList<Integer> gameLengths){
    totalRounds = rounds;
    nodes = nodeList;
    setGameLengths = gameLengths;
    payoffList = payoffs;
  }
  
  public void runTournament() {
    
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
    
    for(int nodeNumber = 0; nodeNumber < nodes.size(); nodeNumber++) {
      Node currentNode = nodes.get(nodeNumber);
      for(int neighbourNumber = 0; neighbourNumber < currentNode.getNeighbours().size(); neighbourNumber++) {
        Node neighbourNode = currentNode.neighbours.get(neighbourNumber);
        if(!neighbourNode.getPlayedAllGames()) {
          Game game1 = new Game(currentNode.getStrategy(), neighbourNode.getStrategy(), first, payoffList);
          game1.playGame();
          Game game2 = new Game(currentNode.getStrategy(), neighbourNode.getStrategy(), second, payoffList);
          game2.playGame();
          Game game3 = new Game(currentNode.getStrategy(), neighbourNode.getStrategy(), third, payoffList);
          game3.playGame();
        }
      }
      nodes.get(nodeNumber).setPlayedAllGames(true);
    }
      
  }
  
  
  

}
