package strategiespack;

import java.util.Random;

public class VaryGameLength {
	
  int totalRounds;
  Random random = new Random();
  public int first;
  public int second;
  public int third;

  public VaryGameLength(int total) {
    totalRounds = total;
    first = 0;
    second = 0;
    third = 0;
  }

  public int getFirstSet() {
    first = random.nextInt(totalRounds) + 1;
    return first;
  }

  public int getSecondSet() {
    if ((totalRounds - first - 1 > 0)) {
      second = random.nextInt(totalRounds - first - 1) + 1;
      return second;
    } else {
      return 0;
    }
  }

  public int getThirdSet() {
    int roundsPlayed = first + second;
    if (roundsPlayed == totalRounds) {
      return 0;
    }
    return totalRounds - first - second;
  }

}
