package testspack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.Evolutionary;
import strategiespack.Node;
import strategiespack.Strategy;

class EvolutionaryTests {
  
  @Test
  void testCreateNode() {
    AlwaysCooperate strategy = new AlwaysCooperate();
    Node node = new Node(strategy);
  }
  
  @Test
  void testGetStrategy() {
    AlwaysCooperate strategy = new AlwaysCooperate();
    Node node = new Node(strategy);
    assertEquals(strategy, node.getStrategy(), "node getter or functioning incorrectly");
  }
  
  @Test
  void testSetStrategy() {
    AlwaysCooperate strategy = new AlwaysCooperate();
    Node node = new Node(strategy);
    AlwaysDefect newStrategy = new AlwaysDefect();
    node.setStrategy(newStrategy);
    assertEquals(newStrategy, node.getStrategy(), "node setter functioning incorrectly");
  }
  
  @Test
  void testGetNeighbours() {
    AlwaysCooperate strategy = new AlwaysCooperate();
    Node node = new Node(strategy);
    ArrayList<Node> nodes = new ArrayList<Node>();
    assertEquals(nodes, node.getNeighbours(), "neighbour getter functioning incorrectly");
  }
  
  @Test
  void testSetNeighbours() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysCooperate strategy2 = new AlwaysCooperate();
    Node node = new Node(strategy1);
    Node neighbourNode = new Node(strategy2);
    ArrayList<Node> nodes = new ArrayList<Node>();
    nodes.add(neighbourNode);
    node.addNeighbour(neighbourNode);
    assertEquals(nodes, node.getNeighbours(), "neighbour setter functioning incorrectly");
  }
  
  @Test
  void testCreateTournament() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysCooperate strategy2 = new AlwaysCooperate();
    Node node1 = new Node(strategy1);
    Node node2 = new Node(strategy2);
    ArrayList<Node> nodes = new ArrayList<Node>();
    nodes.add(node1);
    nodes.add(node2);
    Evolutionary tournament = new Evolutionary(nodes);
  }

}
