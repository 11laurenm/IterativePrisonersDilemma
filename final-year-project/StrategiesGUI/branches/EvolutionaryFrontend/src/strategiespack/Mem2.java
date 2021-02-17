package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**  A strategy that makes the same decisions as tit for tat in the first two moves,
 * then chooses a new strategy to follow every two moves based on the outcome of the last two moves.
 * 
 * @author Lauren Moore -zfac043
 *
 */

public class Mem2 extends Strategy {

  String following = "titForTat";

  /**
   * Constructor for an instance of the strategy, needs no inputs.
  */
  public Mem2() {
  }

  /**
   * returns the decision made by the strategy for the turn in which it is called.
   * @param lastMove - the last move made by its opponent
   * @param opponentHistory - all previous moves made by its opponent in this game
   * @param myHistory - all previous moves made by the strategy
   * @param myLastMove - last move made by the strategy
   * @param opponentPoints - score of opponent
   * @return c, then opponent's previous move, then follows one of three strategies 
   *      based on opponent's decisions
  */
  @Override
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
          ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    if (myHistory.size() == 0) { //start by following tit for tat
      following = "titForTat";
      return 'c';
    }
    if (myHistory.size() % 2 == 0 && following != "alwaysAlwaysDefect") { 
      //every two moves reconsider strategy
      if (lastMove == 'c' && opponentHistory.get(opponentHistory.size() - 2) == 'c') {
        following = "titForTat";
      } else if (((lastMove == 'c' && myLastMove == 'd') 
              || (lastMove == 'd' && myLastMove == 'c')) 
              && ((opponentHistory.get(opponentHistory.size() - 2) == 'c' 
              && myHistory.get(myHistory.size() - 2) == 'd') 
              || (opponentHistory.get(opponentHistory.size() - 2) == 'd' 
              && myHistory.get(myHistory.size() - 2) == 'c'))) {
        //if cd or dc were played on both of previous two turns
        following = "titForTwoTats";
      } else {
        if (following != "alwaysDefect") {
          following = "alwaysDefect";
        } else { //if always defect is chosen twice always choose always defect
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
    return 'd'; //if following alwaysdefect
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
  
  public StringProperty colourProperty() {
    SimpleStringProperty colour = new SimpleStringProperty();
    colour.setValue("Black");
    return colour;
  }

}