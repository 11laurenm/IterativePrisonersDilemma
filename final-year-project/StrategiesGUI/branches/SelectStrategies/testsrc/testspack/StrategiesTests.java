package testspack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.Game;
import strategiespack.Gradual;
import strategiespack.HardMajority;
import strategiespack.HardTitForTat;
import strategiespack.Mem2;
import strategiespack.Mistrust;
import strategiespack.Pavlov;
import strategiespack.PeriodicCCD;
import strategiespack.PeriodicCD;
import strategiespack.PeriodicDDC;
import strategiespack.Prober;
import strategiespack.ScoreBased;
import strategiespack.SoftMajority;
import strategiespack.Spiteful;
import strategiespack.TitForTat;
import strategiespack.TitForTwoTats;
import strategiespack.VaryingMajority;

/** Tests relating to the functionality of the strategy classes.
 * 
 * @author Lauren Moore - zfac043
 *
 */

class StrategiesTests {

  /**
   * First test: tests that the AlwaysCooperate strategy always cooperates.
   * To pass this test I made a class that always returns c for cooperate
   */
  @Test
  void testAlwaysCooperate() {
    AlwaysCooperate testStrat = new AlwaysCooperate();
    ArrayList<Character> testHistory = new ArrayList<>();
    assertEquals(testStrat.getDecision('n', testHistory, testHistory, 'n', 0), 'c', 
            "AlwaysCooperate strategy not returning correct decision");
  }

  /**
   * Second test: tests that the AlwaysDefect strategy always defects.
   * To pass this test I made a class that always returns d for defect
   */
  @Test
  void testAlwaysDefect() {
    AlwaysDefect testStrat = new AlwaysDefect();
    ArrayList<Character> testHistory = new ArrayList<>();
    assertEquals(testStrat.getDecision('n', testHistory, testHistory, 'n', 0), 'd', 
            "AlwaysDefect strategy not returning correct decision");
  }
  
  /**
   * Third test: tests that the Strategy class's get points returns correctly 
   * when 0 points
   * In order to pass this test I added the getPoints method to the Strategy 
   * class and added a points variable to the class to store the amount.
   */
  @Test
  void testGetPointsZeroPoints() {
    AlwaysDefect testStrat = new AlwaysDefect();
    int points = testStrat.getPoints();
    assertEquals(points, 0, "getPoints not returning correctly when 0 points");
  }
  
  /**
   * Fourth test: tests that the method to add points functions correctly.
   * In order to pass this test I added the addPoints method which 
   * adds the amount of points passed to it as a parameter
   */
  @Test
  void testAddPoints() {
    AlwaysDefect testStrat = new AlwaysDefect();
    testStrat.addPoints(2);
    int points = testStrat.getPoints();
    assertEquals(points, 2, "addPoints or getPoints not functioning correctly");
  }
  
  /**
   * Fifth test: tests that the titfortat strategy returns c when no history is available
   * In order to pass this test I created the titfortat class and the getDecision method that 
   * returns d if the last move was d and c otherwise. This means it returns c when no history 
   * is available
   */
  @Test
  void testTitForTatNoHistory() {
    TitForTat testStrat = new TitForTat();
    AlwaysDefect testStrat2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 1, payoffs);
    int points = testStrat.getPoints();
    assertEquals(points, 0, "tit for tat not returning c when no history");
  }
  
  /**
   * Sixth test: tests that the titfortat strategy returns correctly when opponent cooperates.
   * I didn't have to make any changes to pass this test.
   */
  @Test
  void testTitForTatWithHistoryCooperate() {
    TitForTat testStrat = new TitForTat();
    AlwaysCooperate testStrat2 = new AlwaysCooperate();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 2, payoffs);
    game.playGame();
    int points = testStrat.getPoints();
    assertEquals(points, 6, "tit for tat not returning correctly "
        + "against cooperating opponent"); 
  }
  
  /**
   * Seventh test: tests that the titfortat strategy returns correctly when opponent defects.
   * I didn't have to make any changes to pass this test.
   */
  @Test
  void testTitForTatWithHistoryDefect() {
    TitForTat testStrat = new TitForTat();
    AlwaysDefect testStrat2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 2, payoffs);
    game.playGame();
    int points = testStrat.getPoints();
    assertEquals(points, 1, "tit for tat not returning "
        + "correctly against defecting opponent"); 
  }
 
  /**
   * Eighth test: tests that the varyingMajority strategy returns c when no history is available.
   * In order to pass this test I created the varyingMajority class and the getDecision method that 
   * returns c if the size of the history arraylist is 0.
   */
  @Test
  void testVaryingMajorityNoHistory() {
    VaryingMajority testStrat = new VaryingMajority(3);
    AlwaysDefect testStrat2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 1, payoffs);
    game.playGame();
    int points = testStrat.getPoints();
    assertEquals(points, 0, "varyingMajority not returning c when no history");
  }
 
  /**
  * Ninth test: tests that the varyingMajority strategy returns correctly when less history than 
  * the strategy is told to consider is available
  * In order to pass this test I added to the if statement, replacing the number of iterations with 
  * the size of the history arraylist instead of the rounds parameter.`
  * This means that it analyses the whole history while avoiding errors for 
  * attempting to access arraylist indexes that don't exist. 
  */
  @Test
  void testVaryingMajorityWithNotEnoughHistory() {
    VaryingMajority testStrat = new VaryingMajority(3);
    AlwaysDefect testStrat2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 2, payoffs);
    game.playGame();
    int points = testStrat.getPoints();
    assertEquals(points, 1, "varyingMajority not returning correctly with insufficient history");
  }
 
  /**
  * Tenth test: tests that the varyingMajority strategy returns correctly when more history than 
  * the strategy is told to consider.
  * I didn't have to make any changes to pass this test.
  */
  @Test
  void testVaryingMajorityWithTooMuchHistory() {
    VaryingMajority testStrat = new VaryingMajority(3);
    AlwaysDefect testStrat2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 5, payoffs);
    game.playGame();
    int points = testStrat.getPoints();
    assertEquals(points, 4, "varyingMajority not returning correctly with too much history");
  }
  
  /**
   * Eleventh test: tests that the Spiteful strategy returns correctly when 
   * playing against an opponent that only cooperates.
   * In order to pass this test I created the Spiteful class and the getDecision method that 
   * returns c.
   */
  @Test
  void testSpitefulAlwaysCooperate() {
    AlwaysCooperate testStrat = new AlwaysCooperate();
    Spiteful testStrat2 = new Spiteful();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(testStrat2.getPoints(), 9, "Spiteful strategy not functioning correctly");
  }
 
  /**
   * Twelfth test: tests that the Spiteful strategy returns correctly when 
   * playing against an opponent that only defects.
   * In order to pass this test I altered the getDecision method to return 
   *      d if the opponent's history contains a defection
   */
  @Test
  void testSpitefulAlwaysDefect() {
    AlwaysDefect testStrat = new AlwaysDefect();
    Spiteful testStrat2 = new Spiteful();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(testStrat2.getPoints(), 2, "Spiteful strategy not functioning correctly");
  }
  
  /**
   * Thirteenth test: tests that the Mistrust strategy returns correctly when 
   * playing against an opponent that only cooperates.
   * In order to pass this test I created the Mistrust class and the getDecision method that 
   * returns d originally, then the opponent's last move.
   */
  @Test
  void testMistrustAlwaysCooperate() {
    AlwaysCooperate testStrat = new AlwaysCooperate();
    Mistrust testStrat2 = new Mistrust();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(testStrat2.getPoints(), 11, "Mistrust strategy not functioning correctly");
  }
 
  /**
   * Fourteenth test: tests that the Mistrust strategy returns correctly when 
   * playing against an opponent that only defects.
   * I didn't have to make any changes to pass this test.
   */
  @Test
  void testMistrustAlwaysDefect() {
    AlwaysDefect testStrat = new AlwaysDefect();
    Mistrust testStrat2 = new Mistrust();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(testStrat2.getPoints(), 3, "Mistrust strategy not functioning correctly");
  }
  
  /**
   * Fifteenth test: tests that the Pavlov strategy returns correctly when 
   * playing against an opponent that only cooperates.
   * In order to pass this test I created the Pavlov class and the getDecision method that 
   * returns c originally, then c if both participant's previous moves match.
   */
  @Test
  void testPavlovAlwaysCooperate() {
    AlwaysCooperate testStrat = new AlwaysCooperate();
    Pavlov testStrat2 = new Pavlov();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(testStrat2.getPoints(), 9, "Pavlov strategy not functioning correctly");
  }
 
  /**
   * Sixteenth test: tests that the Pavlov strategy returns correctly when 
   * playing against an opponent that only defects.
   * I didn't have to make any changes to pass this test.
   */
  @Test
  void testPavlovAlwaysDefect() {
    AlwaysDefect testStrat = new AlwaysDefect();
    Pavlov testStrat2 = new Pavlov();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(testStrat2.getPoints(), 1, "Pavlov strategy not functioning correctly");
  }
  
  /**
   * Seventeenth test: tests that the SoftMajority strategy returns correctly when 
   * playing against an opponent that only cooperates.
   * In order to pass this test I created the SoftMajority class and the getDecision method that 
   * returns c originally, then c if the opponent's cooperations are equal or more than its
   * defections.
   */
  @Test
  void testSoftMajorityAlwaysCooperate() {
    AlwaysCooperate testStrat = new AlwaysCooperate();
    SoftMajority testStrat2 = new SoftMajority();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(testStrat2.getPoints(), 9, "SoftMajority strategy not functioning correctly");
  }
 
  /**
   * Eighteenth test: tests that the SoftMajority strategy returns correctly when 
   * playing against an opponent that only defects.
   * I didn't have to make any changes to pass this test.
   */
  @Test
  void testSoftMajorityAlwaysDefect() {
    AlwaysDefect testStrat = new AlwaysDefect();
    SoftMajority testStrat2 = new SoftMajority();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(testStrat2.getPoints(), 2, "SoftMajority strategy not functioning correctly");
  }
 
  /**
   * Nineteenth test: tests that the HardMajority strategy returns correctly when 
   * playing against an opponent that only cooperates.
   * In order to pass this test I created the HardMajority class and the getDecision method that 
   * returns c originally, then c if the opponent's cooperations are more than its
   * defections.
   */
  @Test
  void testHardMajorityAlwaysCooperate() {
    AlwaysCooperate testStrat = new AlwaysCooperate();
    HardMajority testStrat2 = new HardMajority();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(testStrat2.getPoints(), 11, "HardMajority strategy not functioning correctly");
  }
 
  /**
   * Twentieth test: tests that the HardMajority strategy returns correctly when 
   * playing against an opponent that only defects.
   * I didn't have to make any changes to pass this test.
   */
  @Test
  void testHardMajorityAlwaysDefect() {
    AlwaysDefect testStrat = new AlwaysDefect();
    HardMajority testStrat2 = new HardMajority();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(testStrat2.getPoints(), 3, "HardMajority strategy not functioning correctly");
  }
  
  /**
   * Twenty first test: tests that the PeriodicDDC strategy returns correctly.
   * In order to pass this test I created the PeriodicDDC class and the getDecision method that 
   * returns d,d,c on a loop.
   */
  @Test
  void testPeriodicDdc() {
    PeriodicDDC testStrat = new PeriodicDDC();
    AlwaysDefect testStrat2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('d', 'd', 'c'));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, "PeriodicDDC strategy not functioning correctly");
  }
 
  /**
   * Twenty second test: tests that the PeriodicDDC strategy returns correctly.
   * In order to pass this test I created the PeriodicDDC class and the getDecision method that 
   * returns c,c,d on a loop.
   */
  @Test
  void testPeriodicCcd() {
    PeriodicCCD testStrat = new PeriodicCCD();
    AlwaysDefect testStrat2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'c', 'd'));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, "PeriodicCCD strategy not functioning correctly");
  }
 
  /**
   * Twenty third test: tests that the PeriodicDDC strategy returns correctly.
   * In order to pass this test I created the PeriodicDDC class and the getDecision method that 
   * returns c,d on a loop.
   */
  @Test
  void testPeriodicCD() {
    PeriodicCD testStrat = new PeriodicCD();
    AlwaysDefect testStrat2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'd'));
    Game game = new Game(testStrat, testStrat2, 2, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, "PeriodicCD strategy not functioning correctly");
  }
 
  /**
   * Twenty fourth test: tests that the TitForTwoTats strategy returns correctly.
   * In order to pass this test I created the TitForTwoTats class and the getDecision method that 
   * returns c for the first two moves, then defect if the opponent defected on both their 
   * previous moves
   */
  @Test
  void testTitForTwoTats() {
    TitForTwoTats testStrat = new TitForTwoTats();
    AlwaysDefect testStrat2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'c', 'd', 'd'));
    Game game = new Game(testStrat, testStrat2, 4, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, 
            "TitForTwoTats strategy not functioning correctly");
  }
 
  /**
   * Twenty fourth test: tests that the HardTitForTat strategy returns correctly.
   * In order to pass this test I created the HardTitForTat class and the getDecision method that 
   * returns c for the first two moves, then defect if the opponent defected on one of their 
   * previous moves
   */
  @Test
  void testHardTitForTat() {
    HardTitForTat testStrat = new HardTitForTat();
    PeriodicCD testStrat2 = new PeriodicCD();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'c', 'd'));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, 
            "HardTitForTat strategy not functioning correctly");
  }
 
  /**
   * Twenty fifth test: tests that the Gradual strategy returns the correct number of defections.
   * In order to pass this test I created the Gradual class and the getDecision method that 
   * returns c until its opponent defects, then defects the amount of times its opponent has 
   * defected, then cooperates twice.
   */
  @Test
  void testGradualFirst() {
    Gradual testStrat = new Gradual();
    PeriodicCCD testStrat2 = new PeriodicCCD();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 
            'c', 'c', 'd', 'c', 'c', 'd', 'd', 'c', 'c'));
    Game game = new Game(testStrat, testStrat2, 10, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, "Gradual strategy not functioning correctly");
  }
 
  /**
   * Twenty sixth test: tests that the Gradual strategy returns the correct number of defections.
   * I didn't have to write any additional code to pass this test
   */
  @Test
  void testGradualSecond() {
    Gradual testStrat = new Gradual();
    PeriodicCD testStrat2 = new PeriodicCD();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 
            'c', 'd', 'c', 'c', 'c', 'd', 'd'));
    Game game = new Game(testStrat, testStrat2, 8, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, "Gradual strategy not functioning correctly");
  }
 
  /**
   * Twenty seventh test: tests that the Prober strategy returns correctly against an opponent 
   * that always cooperates
   * In order to pass this test I created the Prober class and the getDecision method that 
   * returns d, c, c then defects if its opponent cooperated in rounds two and three
   */
  @Test
  void testProberFirst() {
    Prober testStrat = new Prober();
    AlwaysCooperate testStrat2 = new AlwaysCooperate();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('d', 'c', 'c', 'd', 'd'));
    Game game = new Game(testStrat, testStrat2, 5, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, "Prober strategy not functioning correctly");
  }
 
  /**
   * Twenty eighth test: tests that the Prober strategy returns correctly against an opponent 
   * that always defects.
   * I didn't have to write any additional code to pass this test
   */
  @Test
  void testProberSecond() {
    Prober testStrat = new Prober();
    PeriodicCD testStrat2 = new PeriodicCD();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('d', 'c', 'c', 'c', 'd'));
    Game game = new Game(testStrat, testStrat2, 5, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, "Prober strategy not functioning correctly");
  }
 
  /**
   * Twenty ninth test: tests that the Mem2 strategy returns correctly when it should defect
   * In order to pass this test I created the Mem2 class and the getDecision method that 
   * follows tit for tat then reevaluates its strategy, choosing to defect when its opponent's moves 
   * don't match its own
   */
  @Test
  void testMem2First() {
    Mem2 testStrat = new Mem2();
    PeriodicCD testStrat2 = new PeriodicCD();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'c', 
            'd', 'd', 'd', 'd', 'd', 'd'));
    Game game = new Game(testStrat, testStrat2, 8, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, "Mem2 strategy not functioning correctly");
  }
 
  /**
   * Thirtieth test: tests that the Mem2 strategy returns correctly when it should 
   * cooperate through tit for tat
   * In order to pass this test I added to the getDecision method so that it 
   * followed tit for tat when both it and its opponent cooperated both turns
   */
  @Test
  void testMem2Second() {
    Mem2 testStrat = new Mem2();
    AlwaysCooperate testStrat2 = new AlwaysCooperate();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'c', 
            'c', 'c', 'c', 'c', 'c', 'c'));
    Game game = new Game(testStrat, testStrat2, 8, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, "Mem2 strategy not functioning correctly");
  }
 
  /**
   * Thirty first test: tests that the Mem2 strategy returns correctly when it should 
   * follow TitForTwoTats
   * In order to pass this test I added to the getDecision method so that it 
   * followed TitForTwoTats when it and its opponent returned different decisions
   */
  @Test
  void testMem2Third() {
    Mem2 testStrat = new Mem2();
    PeriodicCCD testStrat2 = new PeriodicCCD();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'c', 
            'c', 'd', 'c', 'c', 'd', 'd'));
    Game game = new Game(testStrat, testStrat2, 8, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, "Mem2 strategy not functioning correctly");
  }
 
  /**
   * Thirty Second test: tests that the ScoreBased strategy returns correctly.
   * In order to pass this test I created the ScoreBased class and the getDecision method that 
   * returns d if participants scores are equal or if opponent is losing by more than 9 points
   */
  @Test
  void testScoreBased() {
    ScoreBased testStrat = new ScoreBased();
    AlwaysCooperate testStrat2 = new AlwaysCooperate();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Character> expected = new ArrayList<>(Arrays.asList('d', 'd', 'c'));
    Game game = new Game(testStrat, testStrat2, 3, payoffs);
    game.playGame();
    assertEquals(game.historyStrategy1, expected, "ScoreBased strategy not functioning correctly");
  }

}