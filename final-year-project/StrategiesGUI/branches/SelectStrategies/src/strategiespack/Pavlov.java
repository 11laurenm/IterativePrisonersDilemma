package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pavlov extends Strategy {

  public Pavlov() {
  }
    
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
          ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    if (lastMove == myLastMove) {
      return 'c';
    }
    return 'd';
  }
  
  /**
   * Method that provides the strategy's name.
   * @return the name of the strategy as a SimpleStringProperty
   */
  public StringProperty nameProperty() {
    SimpleStringProperty strategyName = new SimpleStringProperty();
    strategyName.setValue("Pavlov");
    return strategyName;
  }
  
  /**
   * Method that provides the strategy's probability (if one exists).
   * @return the value of the probability variable of the strategy as a SimpleStringProperty
   */
  public StringProperty probabilityProperty() {
    SimpleStringProperty prob = new SimpleStringProperty();
    prob.setValue("-");
    return prob;
  }
  
  /**
   * Method that provides the number of rounds the strategy considers (if one exists).
   * @return the value of the rounds variable of the strategy as a SimpleStringProperty
   */
  public StringProperty roundsProperty() {
    SimpleStringProperty rounds = new SimpleStringProperty();
    rounds.setValue("-");
    return rounds;
  }

}
