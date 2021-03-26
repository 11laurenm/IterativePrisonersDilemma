package strategiespack;

import java.util.ArrayList;

/**
 * Node class where a node represents a node/point in a graph.

 * @author Lauren Moore - zfac043
 *
 */
public class Node {
  
  /**
   * The strategy to be followed by the node.
   */
  Strategy strategy;
  
  /**
   * An ArrayList containing every neighbour of the node.
   */
  ArrayList<Node> neighbours;
  
  /**
   * Set during a generation of the tournament to prevent the strategy playing duplicate games.
   */
  boolean playedAllGames;
  
  /**
   * The node's id, used to identify which nodes are its neighbours.
   */
  String nodeId;
  
  /**
   * Used to update the strategy followed by a node.
   */
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
  
  /**
   * getter for the strategy followed by the node.
   * @return the strategy followed by the node.
   */
  public Strategy getStrategy() {
    return strategy;
  }
  
  /**
   * setter for the strategy followed by the node.
   * @param newStrategy the next strategy to be followed by the node.
   */
  public void setStrategy(Strategy newStrategy) {
    strategy = newStrategy;
  }
  
  /**
   * getter for the neighbours of the node.
   * @return the neighbours of the node.
   */
  public ArrayList<Node> getNeighbours() {
    return neighbours;
  }

  /**
   * setter for the neighbours of the node, adds the parameter node to the list of neighbours.
   * @param neighbourNode the node to be added to the ArrayList of neighbours.
   */
  public void addNeighbour(Node neighbourNode) {
    neighbours.add(neighbourNode);
  }
  
  /**
   * getter for whether or not the node has played all the games it needs to in a generation.
   * @return a boolean, true if all games have been played, false if not
   */
  public boolean getPlayedAllGames() {
    return playedAllGames;
  }
  
  /**
   * getter for whether or not the node has played all the games it needs to in a generation.
   * @param played the boolean value that the variable playedAllGames should be set to
   */
  public void setPlayedAllGames(boolean played) {
    playedAllGames = played;
  }
  
  /**
   * getter for the node's Id.
   * @return the node's Id as a string
   */
  public String getNodeId() {
    return nodeId;
  }
  
  /**
   * getter for the node's Id.
   * @param id - the value to be set as the node's Id
   */
  public void setNodeId(String id) {
    nodeId = id;
  }
}
