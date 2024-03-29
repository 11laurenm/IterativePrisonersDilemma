package guipack;

import java.util.ArrayList;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Callback;
import strategiespack.Strategy;

/**
 * Controller class used to handle the results screen of GUI.

 * @author Lauren Moore - zfac043
 *     Some code adapted from https://code.makery.ch/library/javafx-tutorial/, author Marco Jakob
 */

public class ResultsController {

  /**
   * TableView containing each strategy that was entered 
   * into a tournament and the points they earned.
   */
  @FXML
  private TableView<Strategy> resultsTable;
  
  /**
   * TableColumn that displays the strategy's place in 
   * the tournament.
   */
  @FXML
  private TableColumn<Strategy, String> placeColumn;
  
  /**
   * TableColumn that displays the strategy's name.
   */
  @FXML
  private TableColumn<Strategy, String> strategyColumn;
  
  /**
   * TableColumn that displays the strategy's points.
   */
  @FXML
  private TableColumn<Strategy, String> pointsColumn;
  
  /**
   * GridPane that displays the points earned in each game.
   */
  @FXML
  private GridPane resultsGrid;

  /**
   * Allows the program to access the main function.
   */
  private Main mainn;

  /**
   * A list of strategies used to populate the TableView.
   */
  private ObservableList<Strategy> resultsList = FXCollections.observableArrayList();
    
  /**
   * Called when the controller is created, sets the value of Main variable 
   * and populates the TableView.

   * @param main - the main function
   */
  @FXML void initialize() {
    resultsTable.setItems(resultsList);

    placeColumn.setCellValueFactory(new Callback<CellDataFeatures<Strategy, String>, 
            ObservableValue<String>>() {
      @Override public ObservableValue<String> call(CellDataFeatures<Strategy, String> p) {
        return new ReadOnlyObjectWrapper(resultsTable.getItems().indexOf(p.getValue()) + 1);
      } //creates a column that is automatically numbered
    });  
    strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("name"));
    pointsColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("points"));
  }
  
  /**
   * Used to sort the list of strategies into descending order 
   * based on points so the results are displayed in an easy 
   * to read format.

   * @param results - an unordered arrayList of all strategies
   */
  public void setResults(ArrayList<Strategy> results) {
    ArrayList<Strategy> sortedResults = new ArrayList<>();
    ArrayList<Strategy> tempResults = new ArrayList<>();
    for (int i = 0; i < results.size(); i++) {
      tempResults.add(results.get(i));
    }

    while (tempResults.size() > 0) {
      int highestpos = 0;
      for (int i = 0; i < tempResults.size(); i++) {
        if (tempResults.get(i).getPoints() > tempResults.get(highestpos).getPoints()) {
          highestpos = i;
        }
      }
      sortedResults.add(tempResults.get(highestpos)); //adds the strategy 
      //with highest score to the list
      tempResults.remove(highestpos);
    }
    resultsList = FXCollections.observableArrayList(sortedResults);
    //converts the list to an observable list so the table can display it
  }

  /**
   * Sets the value of the main variable so the main class can be accessed.

   * @param mainclass - an instance of the Main class
   */
  public void setMain(Main mainclass) {
    this.mainn = mainclass;
  }

  /**
   * Closes the dialog when the close button is pressed.
   */
  @FXML
  private void handleBackButton() {
    mainn.closeResults();
  }

  /**
   * Launches the detailed results dialog when the 
   * corresponding button is pressed.
   */
  @FXML
  private void detailedResultsButton() {
    mainn.launchDetailedResults();
  }
  
  /**
   * Runs the method responsible for handling the export of the 
   * results when the corresponding button is pressed.
   */
  @FXML
  private void exportDetailedResultsButton() {
    mainn.exportDetailedResults();
  }

  /**
   * Used to construct and populate the gridpane as it varies in size 
   * depending on how many participating strategies there are.

   * @param results - the list of strategies to add
   * @param scores - the points earned in each game
   */
  void constructGrid(ArrayList<Strategy> results, ArrayList<Integer> scores) {
    
    for (int i = 0; i < results.size() - 1; i++) { //sets all cells to same size
      ColumnConstraints col1 = new ColumnConstraints();
      col1.setPercentWidth(100 / (results.size() + 1));
      RowConstraints row1 = new RowConstraints();
      row1.setPercentHeight(100 / (results.size() + 1));
      resultsGrid.getColumnConstraints().add(col1);
      resultsGrid.getRowConstraints().add(row1);
    }
    
    for (int i = 0; i < results.size(); i++) { //adds strategy names to top and left
      //sides of the grid
      Label label = new Label();
      label.setText(results.get(i).nameProperty().getValue());
      resultsGrid.add(label, 0, i + 1);
      GridPane.setHalignment(label, HPos.CENTER); //aligns label to center of cell
      Label label2 = new Label();
      label2.setText(results.get(i).nameProperty().getValue());
      resultsGrid.add(label2, i + 1, 0);
      GridPane.setHalignment(label2, HPos.CENTER);
    }

    int pos = 0;
    for (int i = 1; i < results.size() + 1; i++) {
      for (int j = i; j < results.size() + 1; j++) { //iterate through each pair of strategies
        Label label = new Label();
        label.setText(Integer.toString(scores.get(pos)) + ", " 
            + Integer.toString(scores.get(pos + 1))); //set label to points earned by 
        //each strategy in a game
        resultsGrid.add(label, i, j); //add label to the grid
        GridPane.setHalignment(label, HPos.CENTER);
        if (i != j) {
          Label label2 = new Label();
          label2.setText(Integer.toString(scores.get(pos + 1)) + ", " 
              + Integer.toString(scores.get(pos)));
          resultsGrid.add(label2, j, i);
          GridPane.setHalignment(label2, HPos.CENTER);
        }
        pos = pos + 2;
      }
    }
  }
}
