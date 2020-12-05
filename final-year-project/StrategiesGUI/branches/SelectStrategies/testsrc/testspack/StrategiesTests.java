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
import strategiespack.Mistrust;
import strategiespack.Pavlov;
import strategiespack.PeriodicCCD;
import strategiespack.PeriodicCD;
import strategiespack.PeriodicDDC;
import strategiespack.RoundRobin;
import strategiespack.SoftMajority;
import strategiespack.Spiteful;
import strategiespack.TitForTat;
import strategiespack.TitForTwoTats;
import strategiespack.VaryingMajority;

/** Tests relating to the functionality of the strategy classes.
 * 
 * @author Lauren Moore
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
    assertEquals(testStrat.getDecision('n', testHistory, testHistory, 'n'), 'c', 
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
    assertEquals(testStrat.getDecision('n', testHistory, testHistory, 'n'), 'd', 
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
	  assertEquals(points, 6, "tit for tat not returning correctly against cooperating opponent"); 
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
	  assertEquals(points, 1, "tit for tat not returning correctly against defecting opponent"); 
  }
 
  /**
   * Eighth test: tests that the varyingMajority strategy returns c when no history is available
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
  * the size of the history arraylist instead of the rounds parameter.`This means that it analyses the 
  * whole history while avoiding errors for attempting to access arraylist indexes that don't exist. 
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
 
 @Test
 void testSpitefulAlwaysCooperate() {
	 AlwaysCooperate testStrat = new AlwaysCooperate();
	 Spiteful testStrat2 = new Spiteful();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	  Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(testStrat2.getPoints(), 9, "Spiteful strategy not functioning correctly");
 }
 
 @Test
 void testSpitefulAlwaysDefect() {
	 AlwaysDefect testStrat = new AlwaysDefect();
	 Spiteful testStrat2 = new Spiteful();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	  Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(testStrat2.getPoints(), 2, "Spiteful strategy not functioning correctly");
 }
 
 @Test
 void testMistrustAlwaysCooperate() {
	 AlwaysCooperate testStrat = new AlwaysCooperate();
	 Mistrust testStrat2 = new Mistrust();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	  Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(testStrat2.getPoints(), 11, "Mistrust strategy not functioning correctly");
 }
 
 @Test
 void testMistrustAlwaysDefect() {
	 AlwaysDefect testStrat = new AlwaysDefect();
	 Mistrust testStrat2 = new Mistrust();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	  Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(testStrat2.getPoints(), 3, "Mistrust strategy not functioning correctly");
 }
 
 @Test
 void testPavlovAlwaysCooperate() {
	 AlwaysCooperate testStrat = new AlwaysCooperate();
	 Pavlov testStrat2 = new Pavlov();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	  Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(testStrat2.getPoints(), 9, "Pavlov strategy not functioning correctly");
 }
 
 @Test
 void testPavlovAlwaysDefect() {
	 AlwaysDefect testStrat = new AlwaysDefect();
	 Pavlov testStrat2 = new Pavlov();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	 Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(testStrat2.getPoints(), 1, "Pavlov strategy not functioning correctly");
 }
 
 @Test
 void testSoftMajorityAlwaysCooperate() {
	 AlwaysCooperate testStrat = new AlwaysCooperate();
	 SoftMajority testStrat2 = new SoftMajority();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	 Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(testStrat2.getPoints(), 9, "SoftMajority strategy not functioning correctly");
 }
 
 @Test
 void testSoftMajorityAlwaysDefect() {
	 AlwaysDefect testStrat = new AlwaysDefect();
	 SoftMajority testStrat2 = new SoftMajority();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	 Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(testStrat2.getPoints(), 2, "SoftMajority strategy not functioning correctly");
 }
 
 @Test
 void testHardMajorityAlwaysCooperate() {
	 AlwaysCooperate testStrat = new AlwaysCooperate();
	 HardMajority testStrat2 = new HardMajority();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	 Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(testStrat2.getPoints(), 11, "HardMajority strategy not functioning correctly");
 }
 
 @Test
 void testHardMajorityAlwaysDefect() {
	 AlwaysDefect testStrat = new AlwaysDefect();
	 HardMajority testStrat2 = new HardMajority();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	 Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(testStrat2.getPoints(), 3, "HardMajority strategy not functioning correctly");
 }
 
 @Test
 void testPeriodicDDC() {
	 PeriodicDDC testStrat = new PeriodicDDC();
	 AlwaysDefect testStrat2 = new AlwaysDefect();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	 ArrayList<Character> expected = new ArrayList<>(Arrays.asList('d', 'd', 'c'));
	 Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(game.HistoryStrategy1, expected, "PeriodicDDC strategy not functioning correctly");
 }
 
 @Test
 void testPeriodicCCD() {
	 PeriodicCCD testStrat = new PeriodicCCD();
	 AlwaysDefect testStrat2 = new AlwaysDefect();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	 ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'c', 'd'));
	 Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(game.HistoryStrategy1, expected, "PeriodicCCD strategy not functioning correctly");
 }
 
 @Test
 void testPeriodicCD() {
	 PeriodicCD testStrat = new PeriodicCD();
	 AlwaysDefect testStrat2 = new AlwaysDefect();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	 ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'd'));
	 Game game = new Game(testStrat, testStrat2, 2, payoffs);
	 game.playGame();
	 assertEquals(game.HistoryStrategy1, expected, "PeriodicCD strategy not functioning correctly");
 }
 
 @Test
 void testTitForTwoTats() {
	 TitForTwoTats testStrat = new TitForTwoTats();
	 AlwaysDefect testStrat2 = new AlwaysDefect();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	 ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'c', 'd', 'd'));
	 Game game = new Game(testStrat, testStrat2, 4, payoffs);
	 game.playGame();
	 assertEquals(game.HistoryStrategy1, expected, "TitForTwoTats strategy not functioning correctly");
 }
 
 @Test
 void testHardTitForTat() {
	 HardTitForTat testStrat = new HardTitForTat();
	 PeriodicCD testStrat2 = new PeriodicCD();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	 ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'c', 'd'));
	 Game game = new Game(testStrat, testStrat2, 3, payoffs);
	 game.playGame();
	 assertEquals(game.HistoryStrategy1, expected, "HardTitForTat strategy not functioning correctly");
 }
 
 @Test
 void testGradualFirst() {
	 Gradual testStrat = new Gradual();
	 PeriodicCCD testStrat2 = new PeriodicCCD();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	 ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'c', 'c', 'd', 'c', 'c', 'd', 'd', 'c', 'c'));
	 Game game = new Game(testStrat, testStrat2, 10, payoffs);
	 game.playGame();
	 assertEquals(game.HistoryStrategy1, expected, "Gradual strategy not functioning correctly");
 }
 
 @Test
 void testGradualSecond() {
	 Gradual testStrat = new Gradual();
	 PeriodicCD testStrat2 = new PeriodicCD();
	 ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
	 ArrayList<Character> expected = new ArrayList<>(Arrays.asList('c', 'c', 'd', 'c', 'c', 'c', 'd', 'd'));
	 Game game = new Game(testStrat, testStrat2, 8, payoffs);
	 game.playGame();
	 assertEquals(game.HistoryStrategy1, expected, "Gradual strategy not functioning correctly");
 }

}