package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** A strategy that plays the moves defect, cooperate, cooperate 
 * then always defects if the opposing strategy cooperated in moves 2 and 3.
 * If not it plays the opponent's previous move.
 * 
 * @author Lauren Moore -zfac043
 *
 */

public class Prober extends Strategy {

  /**
   * Constructor for an instance of the strategy, needs no inputs.
  */
  public Prober() {
  }
  
  /**
   * returns the decision made by the strategy for the turn in which it is called.
   * @param lastMove - the last move made by its opponent
   * @param opponentHistory - all previous moves made by its opponent in this game
   * @param myHistory - all previous moves made by the strategy
   * @param myLastMove - last move made by the strategy
   * @param opponentPoints - score of opponent
   * @return d,c,c then d if opponent cooperated on rounds two and three
  */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
          ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    if (myHistory.size() == 0) {
      return 'd'; //always return d first
    }
    if (myHistory.size() == 1 || myHistory.size() == 2) {
      return 'c'; //always return c on rounds 2 and 3
    }
    if (opponentHistory.get(1) == 'c' && opponentHistory.get(2) == 'c') {
      return 'd'; //if opponents second and third moves follow alwaysdefect
    }
    if (lastMove == 'c') { //follow tit for tat
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
    strategyName.setValue("Prober");
    return strategyName;
  }
  
  /**
   * Method that provides the strategy's probability (if one exists).
   * @return the value of the probability variable of the strategy as a SimpleStringProperty
   */
  public StringProperty probabilityProperty() {
    SimpleStringProperty prob = new SimpleStringProperty();
    prob.setValue("0");
    return prob;
  }
  
  /**
   * Method that provides the number of rounds the strategy considers (if one exists).
   * @return the value of the rounds variable of the strategy as a SimpleStringProperty
   */
  public StringProperty roundsProperty() {
    SimpleStringProperty rounds = new SimpleStringProperty();
    rounds.setValue("0");
    return rounds;
  }
  
  public StringProperty colourProperty() {
    SimpleStringProperty colour = new SimpleStringProperty();
    colour.setValue("Brown");
    return colour;
  }

}