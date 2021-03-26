package testspack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.Evolutionary;
import strategiespack.Node;
import strategiespack.Strategy;
import strategiespack.TitForTatWithForgiveness;

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
  void testGetDisabled() {
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
    ArrayList<Integer> gameLengths = new ArrayList<>();
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
    ArrayList<Integer> gameLengths = new ArrayList<>(Arrays.asList(1, 1, 1));
    Evolutionary tournament = new Evolutionary(nodes, 5, payoffs, gameLengths, 1);
    tournament.setUpTournament();
    tournament.runGeneration();
    ArrayList<Integer> scores = tournament.returnGenerationScores();
    int score = scores.get(0);
    assertEquals(score, 9, "tournament not returning correct scores");
  }
  
  @Test
  void testReflection() {
    AlwaysCooperate testStrat = new AlwaysCooperate();
    Strategy newStrat = null;
    try {
      newStrat = testStrat.getClass().newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    assertNotEquals(newStrat, testStrat, "Strategies are equal but should not be");
    assertEquals(newStrat.nameProperty().get(), "AlwaysCooperate", 
        "New strategy is not of correct type");
  }
  
  @Test
  void testReflectionOfParamaterisedStrategy() {
    TitForTatWithForgiveness testStrat = new TitForTatWithForgiveness(0.4);
    Strategy newStrat = null;
    try {
      newStrat = testStrat.getClass().newInstance();
      newStrat.setProbability(Double.parseDouble(testStrat.probabilityProperty().get()));
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    assertNotEquals(newStrat, testStrat, "Strategies are equal but should not be");
    assertEquals(newStrat.nameProperty().get(), "TitForTatWithForgiveness", 
        "New strategy is not of correct type");
    assertEquals(newStrat.probabilityProperty().get(), "0.4", 
        "New strategy does not have correct probability");
  }
  
  @Test
  void testReflectionOfNonParamaterisedStrategy() {
    AlwaysCooperate testStrat = new AlwaysCooperate();
    Strategy newStrat = null;
    try {
      newStrat = testStrat.getClass().newInstance();
      newStrat.setProbability(Double.parseDouble(testStrat.probabilityProperty().get()));
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    assertNotEquals(newStrat, testStrat, "Strategies are equal but should not be");
    assertEquals(newStrat.nameProperty().get(), "AlwaysCooperate", 
        "New strategy is not of correct type");
  }

}
