package strategiespack;

import java.util.ArrayList;

public class Node {
  
  Strategy strategy;
  ArrayList<Node> neighbours;
  
  public Node(Strategy strat) {
    strategy = strat;
    neighbours = new ArrayList<Node>();
  }
  
  public Strategy getStrategy() {
    return strategy;
  }
  
  public void setStrategy(Strategy newStrategy) {
    strategy = newStrategy;
  }

}
