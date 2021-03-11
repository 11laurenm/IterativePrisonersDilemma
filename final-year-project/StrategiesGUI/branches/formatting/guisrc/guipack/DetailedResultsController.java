package guipack;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import strategiespack.Strategy;

/**
 * Controller class used to handle the detailed results screen of GUI.
 * @author Lauren Moore - zfac043
 *     Some code adapted from https://code.makery.ch/library/javafx-tutorial/, author Marco Jakob
 */

public class DetailedResultsController {

  /**
   * Allows the program to access the main function.
   */
  private Main mainn;

  /**
   * Label that displays the results from each game and round 
   * in the tournament.
   */
  @FXML
  private Label resultsLabel;

  /**
   * ScrollPane allows the user to scroll through content in the page.
   */
  @FXML
  private ScrollPane scrollPane;
  
  /**
   * Sets the value of the main variable so the main class can be accessed.
   * @param mainclass - an instance of the Main class
   */
  public void setMain(Main mainclass) {
    this.mainn = mainclass;
  }

  /**
   * Run when controller is created, currently no complex 
   * method is used to output results so nothing is needed 
   * in this method.
   */
  @FXML void initialize() {
  }

  /**
   * Formats information from the game and roundrobin classes 
   * into a readable format, then displays it using a label.
   * @param decisions - every decision made during the tournament
   * @param points - every set of points earned during the tournament
   * @param strats - every strategy participating in the tournament
   * @param lengths - the length in rounds of each game played
   */
  void setResults(ArrayList<ArrayList<Character>> decisions, 
        ArrayList<ArrayList<Integer>> points, ArrayList<Strategy> strats, 
        ArrayList<Integer> lengths) {
    String labelText = "";
    ArrayList<String> pairings = new ArrayList<>();
    int pairNumber = 0;

    labelText = labelText + "Length of game 1: " + Integer.toString(lengths.get(0));
    labelText = labelText + " Length of game 2: " + Integer.toString(lengths.get(1));
    labelText = labelText + " Length of game 3: " + Integer.toString(lengths.get(2));
    labelText = labelText + "\n";
    labelText = labelText + "\n";

    for (int i = 0; i < strats.size(); i++) {
      for (int j = i; j < strats.size(); j++) { //creates list of every pairing
        pairings.add(strats.get(i).nameProperty().getValue());
        pairings.add(strats.get(j).nameProperty().getValue());
      }
    }
    
    for (int set = 0; set < decisions.size(); set = set + 3) {
      labelText = labelText + pairings.get(pairNumber) + " playing against ";
      labelText = labelText + pairings.get(pairNumber + 1);
      labelText = labelText + "\n";
      pairNumber = pairNumber + 2;
      int roundNumber = 0;
      for (int j = 0; j < 3; j++) {
        ArrayList<Character> decisionsSet = decisions.get(set + j);
        ArrayList<Integer> pointsSet = points.get(set + j);
        for (int i = 0; i < decisionsSet.size(); i = i + 2) {
          roundNumber++;
          labelText = labelText + "Round ";
          labelText = labelText + roundNumber + ": ";
          labelText = labelText + decisionsSet.get(i) + " vs ";
          labelText = labelText + decisionsSet.get(i + 1) + " ";
          labelText = labelText + pointsSet.get(i) + " point/s vs ";
          labelText = labelText + pointsSet.get(i + 1) + " point/s";
          labelText = labelText + "\n";
        }
      }
      labelText = labelText + "\n";
    }

    resultsLabel.setText(labelText);
    scrollPane.setContent(resultsLabel);
  }
}
