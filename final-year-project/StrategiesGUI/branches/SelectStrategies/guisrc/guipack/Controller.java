package guipack;

import guipack.Main;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import strategiespack.RoundRobin;
import strategiespack.Strategy;

/**
 * Controller class used to handle the configure tournaments screen of GUI.
 * @author Lauren Moore - zfac043
 *     Some code adapted from https://code.makery.ch/library/javafx-tutorial/, author Marco Jakob
 */

public class Controller {
  
  /**
  * TableView containing each strategy that could be entered 
  * into a tournament.
  */
  @FXML
  private TableView<Strategy> strategyTable;
  
  /**
   * TableColumn that displays the strategy's name.
   */
  @FXML
  private TableColumn<Strategy, String> strategyColumn;
  
  /**
   * TableColumn that displays the value of the 
   * strategy's probability variable (if one exists).
   */
  @FXML
  private TableColumn<Strategy, String> probabilityColumn;
  
  /**
   * TableColumn that displays the value of the 
   * strategy's rounds variable (if one exists).
   */
  @FXML
  private TableColumn<Strategy, String> roundsColumn;
  
  /**
   * Text fields that make up the editable payoff matrix.
   */
  @FXML
  private TextField cc1;
  @FXML
  private TextField cc2;
  @FXML
  private TextField cd1;
  @FXML
  private TextField cd2;
  @FXML
  private TextField dc1;
  @FXML
  private TextField dc2;
  @FXML
  private TextField dd1;
  @FXML
  private TextField dd2;
  
  /**
   * Text field that allows user to enter the number of rounds 
   * to be played.
   */
  @FXML
  private TextField rounds;

  /**
   * Allows the program to access the main function.
   */
  private Main mainn;

  /**
   * Constructor for an instance of the strategy, needs no inputs.
   */
  public Controller(){
  }

  /**
   * Called when the controller is created, sets the value of Main variable 
   * and populates the TableView.
   * @param main - the main function
   */
  @FXML 
  public void initialize(Main main) {
    setMain(main);
    strategyTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("name"));
    probabilityColumn.setCellValueFactory(new PropertyValueFactory<Strategy, 
            String>("probability"));
    probabilityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    roundsColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("rounds"));
    roundsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
  }

  /**
   * Sets the value of the main variable so the main class can be accessed and 
   * also sets which strategies should be displayed in the table.
   * @param mainclass - an instance of the Main class
   */
  public void setMain(Main mainclass) {
    this.mainn = mainclass;
    strategyTable.setItems(mainn.getStrategyData());
  }

  /**
   * Called when the run button is pressed. It creates a tournament using the 
   * values entered into the GUI for strategies, rounds and payofffs then 
   * launches the display results dialog.
   */
  @FXML
  private void handleRunTournament() {
    ObservableList selectedItems = strategyTable.getSelectionModel().getSelectedItems();
    ArrayList<Strategy> alSelectedItems = new ArrayList<Strategy>(selectedItems);

    ArrayList<Integer> payoffs = new ArrayList<>();

    if (alSelectedItems.size() < 2) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("At least two strategies must be selected");
      alert.showAndWait();
      return;
    }

    try {
      payoffs.add(Integer.parseInt(cc1.getText()));
      payoffs.add(Integer.parseInt(dc1.getText()));
      payoffs.add(Integer.parseInt(dc2.getText()));
      payoffs.add(Integer.parseInt(dd1.getText()));
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Payoff values must be integers");
      alert.showAndWait();
    }
    try {
      RoundRobin tournament = new RoundRobin(alSelectedItems, 
              Integer.parseInt(rounds.getText()), payoffs);
      try {
        tournament.runTournament();
        mainn.setTournament(tournament);
        mainn.displayResults();
      } catch (Exception e) {
        e.printStackTrace();
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Error running tournament");
        alert.showAndWait();
      }
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Round length must be an integer");
      alert.showAndWait();
    }
  }

  /**
   * Run when cc1 is edited, ensures that cc2 matches cc1.
   */
  @FXML
  private void cc1Edited() {
    cc2.setText(cc1.getText());
  }

  /**
   * Run when cc2 is edited, ensures that cc1 matches cc2.
   */
  @FXML
  private void cc2Edited() {
    cc1.setText(cc2.getText());
  }

  /**
   * Run when cd1 is edited, ensures that dc2 matches cd1.
   */
  @FXML
  private void cd1Edited() {
    dc2.setText(cd1.getText());
  }

  /**
   * Run when cd2 is edited, ensures that dc1 matches cd2.
   */
  @FXML
  private void cd2Edited() {
    dc1.setText(cd2.getText());
  }

  /**
   * Run when dc1 is edited, ensures that cd2 matches dc1.
   */
  @FXML
  private void dc1Edited() {
    cd2.setText(dc1.getText());
  }

  /**
   * Run when dc2 is edited, ensures that cd1 matches dc2.
   */
  @FXML
  private void dc2Edited() {
    cd1.setText(dc2.getText());
  }

  /**
   * Run when dd1 is edited, ensures that dd2 matches dd1.
   */
  @FXML
  private void dd1Edited() {
    dd2.setText(dd1.getText());
  }

  /**
   * Run when dd2 is edited, ensures that dd1 matches dd2.
   */
  @FXML
  private void dd2Edited() {
    dd1.setText(dd2.getText());
  }

  /**
   * Called when a cell in the probability column is edited, 
   * sets the relevant strategy's probability variable if it 
   * has one.
   * @param event - contains the new value for probability
   */
  @FXML
  private void probabilityEdited(CellEditEvent<Strategy, String> event) {
    String newValue = event.getNewValue();
    Strategy strat = event.getRowValue();
    strat.setProbability(Integer.parseInt(newValue));
  }

  /**
   * Called when a cell in the rounds column is edited, 
   * sets the relevant strategy's rounds variable if it 
   * has one.
   * @param event - contains the new value for rounds
   */
  @FXML
  private void roundsEdited(CellEditEvent<Strategy, String> event) {
    String newValue = event.getNewValue();
    Strategy strat = event.getRowValue();
    strat.setRounds(Integer.parseInt(newValue));
  }
}

