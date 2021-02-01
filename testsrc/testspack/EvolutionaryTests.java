package testspack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

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
  void testGetDisabled(){
    AlwaysCooperate strategy = new AlwaysCooperate();
    Node node = new Node(strategy);
    assertEquals(node.getPlayedAllGames(), false, "disabled getter functioning incorrectly");
  }
  
  @Test
  void testSetDisabled() {
    AlwaysCooperate strategy = new AlwaysCooperate();
    Node node = new Node(strategy);
    node.setPlayedAllGames(true);
    assertEquals(node.getPlayedAllGames(), true, "disabled getter functioning incorrectly");
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
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Integer> gameLengths = new ArrayList();
    Evolutionary tournament = new Evolutionary(nodes, 5, payoffs, gameLengths, 4);
  }
  
  @Test
  void testRunTournament() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysCooperate strategy2 = new AlwaysCooperate();
    Node node1 = new Node(strategy1);
    Node node2 = new Node(strategy2);
    ArrayList<Node> nodes = new ArrayList<Node>();
    node1.addNeighbour(node2);
    node2.addNeighbour(node1);
    nodes.add(node1);
    nodes.add(node2);
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Integer> gameLengths = new ArrayList(Arrays.asList(1, 1, 1));
    Evolutionary tournament = new Evolutionary(nodes, 5, payoffs, gameLengths, 1);
    tournament.runTournament();
    assertEquals(node1.getStrategy().getPoints(), 9, "tournament not returning correct scores");
  }

}
