package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Superclass for all strategies.
 * @author Lauren Moore
 *
 */
public class Strategy {
    
  /**
   * The total amount of points the strategy has gained.
   */
  int points;

  int tournamentPosition;

  /**
   * Initialise the strategy with 0 points.
   */
  public Strategy() {
    points = 0;
    tournamentPosition = 0;
  }

  /**
   * Adds a specified amount of points to its score.
   * @param pointsToAdd - the amount of points to be added to the overall score.
   */
  public void addPoints(int pointsToAdd) {
    points = points + pointsToAdd;
  }

  /**
   * Gets the amount of points the strategy has.
   * @return the amount of points the strategy has.
   */
  public int getPoints() {
    return points;
  }

  /**
   * Gets the decision made by the strategy for a particular round.
   * @return the decision made.
   */
  public char getDecision() {
    return 'n';
  }

  /**
   * Gets the decision made by the strategy for a particular round.
   * @return the decision made.
   */
  public char getDecision(char lastMove, ArrayList<Character> opponentHistory, 
          ArrayList<Character> myHistory, char myLastMove, int opponentPoints) {
    return 'n';
  }

  public void setProbability(double prob) {
    ;
  }

  public void setRounds(int round) {
    ;
  }

  public StringProperty positionProperty() {
    SimpleStringProperty pos = new SimpleStringProperty();
    pos.setValue(Integer.toString(tournamentPosition));
    return pos;
  }

  public StringProperty nameProperty() {
    SimpleStringProperty name = new SimpleStringProperty();
    name.setValue("Strategy name");
    return name;
  }

}