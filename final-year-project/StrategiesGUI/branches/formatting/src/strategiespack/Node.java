package strategiespack;

import java.util.ArrayList;

public class Node {
  
  Strategy strategy;
  ArrayList<Node> neighbours;
  boolean playedAllGames;
  String nodeId;
  Strategy newStrategy;
  
  public Node(Strategy strat) {
    strategy = strat;
    neighbours = new ArrayList<Node>();
    playedAllGames = false;
    nodeId = "";
    newStrategy = strat;
  }
  
  public Strategy getStrategy() {
    return strategy;
  }
  
  public void setStrategy(Strategy newStrategy) {
    strategy = newStrategy;
  }
  
  public ArrayList<Node> getNeighbours() {
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
  
  public String getNodeId() {
    return nodeId;
  }
  
  public void setNodeId(String id) {
    nodeId = id;
  }
}
