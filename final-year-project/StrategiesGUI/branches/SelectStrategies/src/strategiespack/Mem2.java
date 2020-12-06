package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mem2 extends Strategy {

  String following = "titForTat";

  public Mem2() {
  }

  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
          ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    if (myHistory.size() == 0) {
      following = "titForTat";
      return 'c';
    }
    if (myHistory.size() % 2 == 0 && following != "alwaysAlwaysDefect") {
      if (lastMove == 'c' && opponentHistory.get(opponentHistory.size() - 2) == 'c') {
        following = "titForTat";
      } else if (((lastMove == 'c' && myLastMove == 'd') 
              || (lastMove == 'd' && myLastMove == 'c')) 
              && ((opponentHistory.get(opponentHistory.size() - 2) == 'c' 
              && myHistory.get(myHistory.size() - 2) == 'd') 
              || (opponentHistory.get(opponentHistory.size() - 2) == 'd' 
              && myHistory.get(myHistory.size() - 2) == 'c'))) {
        following = "titForTwoTats";
      } else {
        if (following != "alwaysDefect") {
          following = "alwaysDefect";
        } else {
          following = "alwaysAlwaysDefect";
        }
      }
    }
    
    if (following == "titForTat") {
      return lastMove;
    }
    if (following == "titForTwoTats") {
      if (lastMove == 'd' && myHistory.get(myHistory.size() - 2) == 'd') {
        return 'd';
      }
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
    strategyName.setValue("AlwaysCooperate");
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