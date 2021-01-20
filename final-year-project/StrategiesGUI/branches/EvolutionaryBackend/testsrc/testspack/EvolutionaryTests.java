package testspack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.Evolutionary;
import strategiespack.Strategy;

class EvolutionaryTests {

  @Test
  void testCreateTournament() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysDefect strategy2 = new AlwaysDefect();
    ArrayList<Strategy> strats = new ArrayList<>();
    strats.add(strategy1);
    strats.add(strategy2);
    Evolutionary tournament = new Evolutionary(2, 4, strats);
  }

}
