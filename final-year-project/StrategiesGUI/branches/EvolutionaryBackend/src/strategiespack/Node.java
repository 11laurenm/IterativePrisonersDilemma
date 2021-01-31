package strategiespack;

import java.util.ArrayList;

public class Node {
  
  Strategy strategy;
  ArrayList<Node> neighbours;
  boolean playedAllGames;
  
  public Node(Strategy strat) {
    strategy = strat;
    neighbours = new ArrayList<Node>();
    playedAllGames = false;
  }
  
  public Strategy getStrategy() {
    return strategy;
  }
  
  public void setStrategy(Strategy newStrategy) {
    strategy = newStrategy;
  }
  
  public ArrayList<Node> getNeighbours(){
    return neighbours;
  }

  public void addNeighbour(Node neighbourNode) {
    neighbours.add(neighbourNode);
  }
  
  public boolean getPlayedAllGames() {
    return playedAllGames;
  }
  
  public void setPlayedAllGames(boolean played) {
    playedAllGames = played;
  }
}
