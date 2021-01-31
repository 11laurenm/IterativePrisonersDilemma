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
  void testCreateTournament() {
    Evolutionary tournament = new Evolutionary();
  }
  
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

}
