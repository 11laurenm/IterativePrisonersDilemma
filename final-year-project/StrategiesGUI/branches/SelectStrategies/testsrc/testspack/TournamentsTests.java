package testspack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.RoundRobin;
import strategiespack.Strategy;
import strategiespack.TitForTat;

class TournamentsTests {

  @Test
  void testCooperateDefectTournament() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysDefect strategy2 = new AlwaysDefect();
    ArrayList<Strategy> strats = new ArrayList<>();
    strats.add(strategy1);
    strats.add(strategy2);
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    RoundRobin tournament = new RoundRobin(strats, 10, payoffs);
    tournament.runTournament();
    assertEquals(strategy1.getPoints(), 30, 
            "Round robin not running properly with Cooperate Defect");
    assertEquals(strategy2.getPoints(), 60, 
            "Round robin not running properly with Cooperate Defect");
  }

  @Test
  void testCooperateTitForTatTournament() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    TitForTat strategy2 = new TitForTat();
    ArrayList<Strategy> strats = new ArrayList<>();
    strats.add(strategy1);
    strats.add(strategy2);
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    RoundRobin tournament = new RoundRobin(strats, 10, payoffs);
    tournament.runTournament();
    assertEquals(strategy1.getPoints(), 60, 
            "Round robin not running properly with Cooperate TFT 1");
    assertEquals(strategy2.getPoints(), 60, 
            "Round robin not running properly with Cooperate TFT 2");
  }

}
