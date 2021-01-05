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

/** Tests relating to the functionality of the strategy classes.
 * 
 * @author Lauren Moore - zfac043
 *
 */

class TournamentsTests {

  /**
   * First test: Tests that a roundrobin tournament can be set up and run, 
   * as well as testing that it returns the correct number of points for 
   * the strategies involved
   * In order to pass this test I created the RoundRobin class with the 
   * runTournament method, which iterates through the list of strategies to 
   * run a game with every possible pairing
   */
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

  /**
   * Second test: Tests that a roundrobin tournament can be set up and run, 
   * as well as testing that it returns the correct number of points for 
   * the strategies involved
   * I did not have to write any additional code to pass this test
   */
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
