package strategiespack;

import java.util.ArrayList;

/**
 * Node class where a node represents a node/point in a graph.

 * @author Lauren Moore - zfac043
 *
 */
public class Node {
  
  Strategy strategy;
  ArrayList<Node> neighbours;
  boolean playedAllGames;
  String nodeId;
  Strategy newStrategy;
  
  /**
   * Constructor for a node which assigns it a strategy.

   * @param strat - the strategy which is associated with this node
   */
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
