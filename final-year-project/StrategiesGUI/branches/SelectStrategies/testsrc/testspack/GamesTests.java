package testspack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.Game;
import strategiespack.VaryGameLength;

/** Tests relating to the functionality of the game class.
 * 
 * @author Lauren Moore
 *
 */

class GamesTests {

  /**
   * First test: Tests that a game can be created, and supplied with two strategies
   * and a number of rounds
   * To pass this test I created a game class with a constructor that accepts
   * two strategies and a number of rounds.
   */
  @Test
  void testCreateGame() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysDefect strategy2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(strategy1, strategy2, 3, payoffs);
  }

  /**
   * Second test: Tests that the method getLastMove returns n when no history exists
   * To pass this test I created a method getLastMove and two arraylists, 
   * one for each strategy's history. The method then returns the most recent entry 
   * in the arraylist passed as a parameter.
   */
  @Test
  void testgetLastMoveNoMoves() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysDefect strategy2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(strategy1, strategy2, 3, payoffs);
    char last = game.getLastMove(game.historyStrategy1);
    assertEquals(last, 'n', "getLastMove not returning correctly when 0 moves");
  }

  /**
   * Third test: Tests that the method getLastMove returns the opponents last move 
   * when a history exists.
   * To pass this test I did not have to make any additional changes.
   */
  @Test
  void testgetLastMove() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysDefect strategy2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(strategy1, strategy2, 3, payoffs);
    game.setLastMove('c', game.historyStrategy1);
    char last = game.getLastMove(game.historyStrategy1);
    assertEquals(last, 'c', "getLastMove not returning correctly");
  }

  /**
   * Fourth test: Tests that the method setLastMove adds a move to the history.
   * In order to pass this test I added a setLastMove method that took a character 
   * (move) and arraylist (history) as parameters and adds the character to the arraylist
   */
  @Test
  void testsetLastMove() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysDefect strategy2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(strategy1, strategy2, 3, payoffs);
    game.setLastMove('c', game.historyStrategy1);
    game.setLastMove('d', game.historyStrategy1);
    game.setLastMove('d', game.historyStrategy1);
    ArrayList<Character> testlist = new ArrayList<>(); 
    testlist.add('c');
    testlist.add('d');
    testlist.add('d');
    assertEquals(testlist, game.historyStrategy1, "setLastMove not functioning correctly");  
  }

  /**
   * Fifth test: This tests whether playGame is correctly generating and returning 
   * moves
   * In order to pass this test I created a playGame method and added calls to each 
   * strategy's getDecision and setLastMove methods.
   */
  @Test
  void testPlayGameHistory() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysDefect strategy2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(strategy1, strategy2, 3, payoffs);
    game.playGame();
    assertEquals('c', game.getLastMove(game.historyStrategy1), 
            "strategy1 getLastMove not working in playGame method");
    assertEquals('d', game.getLastMove(game.historyStrategy2), 
            "strategy2 getLastMove not working in playGame method");
  }

  /**
   * Sixth test: This tests whether the method used to calculate the scores works correctly.
   * In order to pass this test I created the method calculateScores which takes both decisions as
   * parameters and returns an arraylist containing both player's scores based on their decisions
   */
  @Test
  void testCalculateScoresCooperate() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysCooperate strategy2 = new AlwaysCooperate();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(strategy1, strategy2, 1, payoffs);
    game.playGame();
    assertEquals(3, strategy1.getPoints(), "calculateScores not functioning correctly");
    assertEquals(3, strategy2.getPoints(), "calculateScores not functioning correctly");
  }

  /**
   * Seventh test: This tests whether the calculate scores method works with different inputs.
   * I didn't make any changes to the code to pass this test.
   */
  @Test
  void testCalculateScoresDefect() {
    AlwaysDefect strategy1 = new AlwaysDefect();
    AlwaysDefect strategy2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(strategy1, strategy2, 3, payoffs);
    ArrayList<Integer> actualscores = game.calculateScores(strategy1.getDecision(), 
            strategy2.getDecision());
    ArrayList<Integer> testscores = new ArrayList();
    testscores.add(1);
    testscores.add(1);
    assertEquals(actualscores, testscores, "calculateScores not functioning correctly");
  }

  /**
   * Eighth test: This tests whether the calculate scores method works with different inputs.
   * I didn't make any changes to the code to pass this test.
   */
  @Test
  void testCalculateScoresCooperateDefect() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysDefect strategy2 = new AlwaysDefect();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(strategy1, strategy2, 1, payoffs);
    game.playGame();
    assertEquals(0, strategy1.getPoints(), "calculateScores not functioning correctly");
    assertEquals(5, strategy2.getPoints(), "calculateScores not functioning correctly");
  }

  /**
   * Ninth test: This tests whether the calculate scores method works with different inputs.
   * I didn't make any changes to the code to pass this test.
   */
  @Test
  void testCalculateScoresDefectCooperate() {
    AlwaysDefect strategy1 = new AlwaysDefect();
    AlwaysCooperate strategy2 = new AlwaysCooperate();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(strategy1, strategy2, 1, payoffs);
    game.playGame();
    assertEquals(5, strategy1.getPoints(), "calculateScores not functioning correctly");
    assertEquals(0, strategy2.getPoints(), "calculateScores not functioning correctly");
  }

  /**
   * Tenth test: This tests whether the playGame method plays the correct number of rounds
   * In order to pass this test I enclosed the code in the playGame method in a for loop, 
   * which used the rounds variable to decide how many rounds to play.
   */
  @Test
  void testPlayCorrectNumberRounds() {
    AlwaysDefect strategy1 = new AlwaysDefect();
    AlwaysCooperate strategy2 = new AlwaysCooperate();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(strategy1, strategy2, 3, payoffs);
    game.playGame();
    assertEquals(strategy1.getPoints(), 15, "Point values not correct, "
            + "possibly not playing correct number of rounds");
    assertEquals(strategy2.getPoints(), 0, "Point values not correct, "
            + "possibly not playing correct number of rounds");
  }

  @Test
  void testEqualStrategies() {
    AlwaysCooperate strategy = new AlwaysCooperate();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    Game game = new Game(strategy, strategy, 1, payoffs);
    game.playGame();
    assertEquals(strategy.getPoints(), 3, "Game not recognising should be dummy strategy");
  }

  @Test
  void testVaryGameLengthFirst() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysCooperate strategy2 = new AlwaysCooperate();
    VaryGameLength vary = new VaryGameLength(50);
    int first = vary.getFirstSet();
    assertTrue(first < 51, "getFirstSet returning too high a number");
    assertTrue(first > 0, "getFirstSet returning too low a number");
  }

  @Test
  void testVaryGameLengthSecond() { 
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysCooperate strategy2 = new AlwaysCooperate();
    VaryGameLength vary = new VaryGameLength(50);
    int first = vary.getFirstSet();
    int second = vary.getSecondSet();
    assertTrue(second <= 50 - first, "getSecondSet returning too high a number");
    assertTrue(first > 0, "getSecondSet returning too low a number");
  }

  @Test
  void testVaryGameLengthFirstTotalRounds() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysCooperate strategy2 = new AlwaysCooperate();
    VaryGameLength vary = new VaryGameLength(50);
    vary.first = 50;
    int second = vary.getSecondSet();
    assertEquals(0, second, "Not behaving correctly when total rounds reached");
  }

  @Test
  void testVaryGameLengthThird() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysCooperate strategy2 = new AlwaysCooperate();
    VaryGameLength vary = new VaryGameLength(50);
    vary.first = 20;
    vary.second = 10;
    int third = vary.getThirdSet();
    assertEquals(third, 20, "getThirdSet returning incorrect number");
  }

  @Test
  void testVaryGameLengthSecondTotalRounds() {
    AlwaysCooperate strategy1 = new AlwaysCooperate();
    AlwaysCooperate strategy2 = new AlwaysCooperate();
    VaryGameLength vary = new VaryGameLength(50);
    vary.first = 27;
    vary.second = 23;
    int third = vary.getThirdSet();
    assertEquals(0, third, "Not behaving correctly when total rounds reached");
  }
 
}