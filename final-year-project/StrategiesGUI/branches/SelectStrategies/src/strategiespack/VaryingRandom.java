package strategiespack;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VaryingRandom extends Strategy{
  
  Random random = new Random();
  float probability;	
 
  public VaryingRandom(float prob) {
     probability = prob;
  }
  
  @Override
  public char getDecision() {
    int randomValue = random.nextInt(10);
    if(randomValue < probability) {
    	return 'c';
    }
    return 'd';
  }
  
  public StringProperty nameProperty() {
	  SimpleStringProperty StrategyName = new SimpleStringProperty();
	  StrategyName.setValue("VaryingRandom");
	  return StrategyName;
  }
  
  public StringProperty probabilityProperty() {
	  SimpleStringProperty prob = new SimpleStringProperty();
	  prob.setValue(Float.toString(probability));
	  return prob;
  }
  
  public StringProperty roundsProperty() {
	  SimpleStringProperty round = new SimpleStringProperty();
	  round.setValue("-");
	  return round;
  }
  
  @Override
  public void setProbability(float prob) {
		this.probability = prob;
  }
}