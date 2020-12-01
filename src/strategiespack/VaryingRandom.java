package strategiespack;

import java.util.Random;

public class VaryingRandom {
  
  Random random = new Random();
  int probability;	
 
  public VaryingRandom(int prob) {
     prob = probability;
  }

  public char getDecision() {
    int randomValue = random.nextInt(10);
    if(randomValue < probability) {
    	return 'c';
    }
    return 'd';
  }
  
}