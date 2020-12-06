package strategiespack;

import java.util.Random;

/**
 * Class responsible for deciding the length of each of the three games that 
 * each pairing plays.
 * @author Lauren Moore - zfac043
 *
 */

public class VaryGameLength {

  /**
   * The total number of rounds to be played over the course of 
   * three games.
   */
  int totalRounds;
  
  /**
   * Random number generator used during decision making.
   */
  Random random = new Random();
  
  /**
   * The number of rounds to be played in the first game.
   */
  public int first;
  
  /**
   * The number of rounds to be played in the second game.
   */
  public int second;
  
  /**
   * The number of rounds to be played in the third game.
   */
  public int third;

  /**
   * Constructor that sets the value of total to the value passed as a parameter 
   * and first, second and third to 0.
   * @param total The total number of rounds to be played over the course of 
   *      three games.
   */
  public VaryGameLength(int total) {
    totalRounds = total;
    first = 0;
    second = 0;
    third = 0;
  }

  /**
   * Decides the amount of rounds in the first game using random 
   * number generation.
   * @return first - the amount of rounds in the first game
   */
  public int getFirstSet() {
    first = random.nextInt(totalRounds) + 1;
    return first;
  }

  /**
   * Decides the amount of rounds in the second game using random 
   * number generation.
   * @return second - the amount of rounds in the second game
   */
  public int getSecondSet() {
    if ((totalRounds - first - 1 > 0)) {
      second = random.nextInt(totalRounds - first - 1) + 1;
      return second;
    } else {
      return 0;
    }
  }

  /**
   * Decides the amount of rounds in the third game by 
   * subtracting the first and second values from the total
   * @return third - the amount of rounds in the third game
   */
  public int getThirdSet() {
    int roundsPlayed = first + second;
    if (roundsPlayed == totalRounds) {
      return 0;
    }
    return totalRounds - first - second;
  }

}
