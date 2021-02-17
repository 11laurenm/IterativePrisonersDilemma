package strategiespack;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Superclass for all strategies.
 * @author Lauren Moore - zfac043
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

  /**
   * Sets the value of the strategy's probability variable if it exists.
   * @param prob - the new probability value
   */
  public void setProbability(double prob) {
    ;
  }

  /**
   * Sets the value of the strategy's rounds variable if it exists.
   * @param round - the new rounds value
   */
  public void setRounds(int round) {
    ;
  }

  /**
   * Returns a stringProperty that allows the program to create 
   * a numbered table of results.
   * @return the number to be shown associated with this strategy
   */
  public StringProperty positionProperty() {
    SimpleStringProperty pos = new SimpleStringProperty();
    pos.setValue(Integer.toString(tournamentPosition));
    return pos;
  }

  /**
   * Method that provides the strategy's name.
   * @return the name of the strategy as a SimpleStringProperty
   */
  public StringProperty nameProperty() {
    SimpleStringProperty name = new SimpleStringProperty();
    name.setValue("Strategy name");
    return name;
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
    SimpleStringProperty roundsProp = new SimpleStringProperty();
    roundsProp.setValue("0");
    return roundsProp;
  }
  
  public StringProperty colourProperty() {
    SimpleStringProperty colour = new SimpleStringProperty();
    colour.setValue("-");
    return colour;
  }

}