package testspack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.Game;
import strategiespack.HardMajority;
import strategiespack.Mistrust;
import strategiespack.Pavlov;
import strategiespack.RoundRobin;
import strategiespack.SoftMajority;
import strategiespack.Spiteful;
import strategiespack.TitForTat;
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
    assertEquals(testStrat.getDecision('n', testHistory, 'n'), 'c', 
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
    assertEquals(testStrat.getDecision('n', testHistory, 'n'), 'd', 
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
	  Game game = new Game(testStrat, testStrat2, 1);
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
	  Game game = new Game(testStrat, testStrat2, 2);
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
	  Game game = new Game(testStrat, testStrat2, 2);
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
	 Game game = new Game(testStrat, testStrat2, 1);
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
	 Game game = new Game(testStrat, testStrat2, 2);
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
	 Game game = new Game(testStrat, testStrat2, 5);
	 game.playGame();
	 int points = testStrat.getPoints();
	 assertEquals(points, 4, "varyingMajority not returning correctly with too much history");
 }
 
 @Test
 void testSpitefulAlwaysCooperate() {
	 AlwaysCooperate strategy1 = new AlwaysCooperate();
	 Spiteful strategy2 = new Spiteful();
	 Game game = new Game(strategy1, strategy2, 3);
	 game.playGame();
	 assertEquals(strategy2.getPoints(), 9, "Spiteful strategy not functioning correctly");
 }
 
 @Test
 void testSpitefulAlwaysDefect() {
	 AlwaysDefect strategy1 = new AlwaysDefect();
	 Spiteful strategy2 = new Spiteful();
	 Game game = new Game(strategy1, strategy2, 3);
	 game.playGame();
	 assertEquals(strategy2.getPoints(), 2, "Spiteful strategy not functioning correctly");
 }
 
 @Test
 void testMistrustAlwaysCooperate() {
	 AlwaysCooperate strategy1 = new AlwaysCooperate();
	 Mistrust strategy2 = new Mistrust();
	 Game game = new Game(strategy1, strategy2, 3);
	 game.playGame();
	 assertEquals(strategy2.getPoints(), 11, "Mistrust strategy not functioning correctly");
 }
 
 @Test
 void testMistrustAlwaysDefect() {
	 AlwaysDefect strategy1 = new AlwaysDefect();
	 Mistrust strategy2 = new Mistrust();
	 Game game = new Game(strategy1, strategy2, 3);
	 game.playGame();
	 assertEquals(strategy2.getPoints(), 3, "Mistrust strategy not functioning correctly");
 }
 
 @Test
 void testPavlovAlwaysCooperate() {
	 AlwaysCooperate strategy1 = new AlwaysCooperate();
	 Pavlov strategy2 = new Pavlov();
	 Game game = new Game(strategy1, strategy2, 3);
	 game.playGame();
	 assertEquals(strategy2.getPoints(), 9, "Pavlov strategy not functioning correctly");
 }
 
 @Test
 void testPavlovAlwaysDefect() {
	 AlwaysDefect strategy1 = new AlwaysDefect();
	 Pavlov strategy2 = new Pavlov();
	 Game game = new Game(strategy1, strategy2, 3);
	 game.playGame();
	 assertEquals(strategy2.getPoints(), 1, "Pavlov strategy not functioning correctly");
 }
 
 @Test
 void testSoftMajorityAlwaysCooperate() {
	 AlwaysCooperate strategy1 = new AlwaysCooperate();
	 SoftMajority strategy2 = new SoftMajority();
	 Game game = new Game(strategy1, strategy2, 3);
	 game.playGame();
	 assertEquals(strategy2.getPoints(), 9, "SoftMajority strategy not functioning correctly");
 }
 
 @Test
 void testSoftMajorityAlwaysDefect() {
	 AlwaysDefect strategy1 = new AlwaysDefect();
	 SoftMajority strategy2 = new SoftMajority();
	 Game game = new Game(strategy1, strategy2, 3);
	 game.playGame();
	 assertEquals(strategy2.getPoints(), 2, "SoftMajority strategy not functioning correctly");
 }
 
 @Test
 void testHardMajorityAlwaysCooperate() {
	 AlwaysCooperate strategy1 = new AlwaysCooperate();
	 HardMajority strategy2 = new HardMajority();
	 Game game = new Game(strategy1, strategy2, 3);
	 game.playGame();
	 assertEquals(strategy2.getPoints(), 11, "HardMajority strategy not functioning correctly");
 }
 
 @Test
 void testHardMajorityAlwaysDefect() {
	 AlwaysDefect strategy1 = new AlwaysDefect();
	 HardMajority strategy2 = new HardMajority();
	 Game game = new Game(strategy1, strategy2, 3);
	 game.playGame();
	 assertEquals(strategy2.getPoints(), 3, "HardMajority strategy not functioning correctly");
 }

}