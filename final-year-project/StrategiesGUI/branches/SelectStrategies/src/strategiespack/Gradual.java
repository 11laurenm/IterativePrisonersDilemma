package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Gradual extends Strategy {

  int defectsLeft;
  int cooperatesLeft;
    
  public Gradual() {
    defectsLeft = 0;
    cooperatesLeft = 0;
  }
    
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
          ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
      
    if (myHistory.size() == 0) {
      defectsLeft = 0;
      cooperatesLeft = 0;
      return 'c';
    }
    if (defectsLeft > 0) {
      if (defectsLeft == 1) {
        defectsLeft--;
        cooperatesLeft = 2;
        return 'd';
      }
      defectsLeft--;
      return 'd';
    }
    if (cooperatesLeft > 0) {
      cooperatesLeft--;
      return 'c';
    }
    if (lastMove == 'd') {
      for (char move : opponentHistory) {
        if (move == 'd') {
          defectsLeft++;
        }
      }
      if (defectsLeft == 1) {
        cooperatesLeft = 2;
      }
      defectsLeft--;
      return 'd';
    }
    return 'c';
  }

  /**
   * Method that provides the strategy's name.
   * @return the name of the strategy as a SimpleStringProperty
   */
  public StringProperty nameProperty() {
    SimpleStringProperty strategyName = new SimpleStringProperty();
    strategyName.setValue("Gradual");
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