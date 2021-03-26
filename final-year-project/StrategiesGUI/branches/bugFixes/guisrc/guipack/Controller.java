package guipack;

import guipack.Main;
import java.util.ArrayList;
import java.util.Random;
import javafx.collections.ObservableList;
import java.awt.Desktop;
import java.io.File;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import strategiespack.RoundRobin;
import strategiespack.Strategy;

/**
 * Controller class used to handle the configure tournaments screen of GUI.

 * @author Lauren Moore - zfac043
 *     Some code adapted from https://code.makery.ch/library/javafx-tutorial/, author Marco Jakob
 *     Code for opening pdf adapted from https://mkyong.com/java/how-to-open-a-pdf-file-in-java/, author Mkyong
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
  
  @FXML
  private Label cooperate1;
  @FXML
  private Label cooperate2;
  @FXML
  private Label defect1;
  @FXML
  private Label defect2;
  
  
  /**
   * Text field that allows user to enter the number of rounds 
   * to be played.
   */
  @FXML
  private TextField rounds;
  
  @FXML
  private TextField game1;
  
  @FXML
  private TextField game2;
  
  @FXML
  private TextField game3;
  
  @FXML
  private CheckBox roundsCheckBox;
  
  @FXML
  private CheckBox gamesCheckBox;
  
  @FXML
  private Button resetPayoffsButton;

  Random random;
  
  RoundRobin tournament;
  
  ArrayList<Integer> gameLengths;
  
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
    //line above allows user to select more than one strategy
    strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("name"));
    probabilityColumn.setCellValueFactory(new PropertyValueFactory<Strategy, 
            String>("probability"));
    probabilityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    roundsColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("rounds"));
    roundsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    GridPane.setHalignment(cooperate1, HPos.CENTER); //set label to center of grid
    GridPane.setHalignment(cooperate2, HPos.CENTER);
    GridPane.setHalignment(defect1, HPos.CENTER);
    GridPane.setHalignment(defect2, HPos.CENTER);
  }

  /**
   * Sets the value of the main variable so the main class can be accessed and 
   * also sets which strategies should be displayed in the table.

   * @param mainclass - an instance of the Main class
   */
  public void setMain(Main mainclass) {
    this.mainn = mainclass;
    strategyTable.setItems(mainn.getStrategyData()); //fills the tableview with strategies
  }

  /**
   * Called when the run button is pressed. It creates a tournament using the 
   * values entered into the GUI for strategies, rounds and payofffs then 
   * launches the display results dialog.
   */
  @FXML
  private void handleRunTournament() {
    ObservableList<Strategy> selectedItems = strategyTable.getSelectionModel().getSelectedItems();
    ArrayList<Strategy> alSelectedItems = new ArrayList<Strategy>(selectedItems);
    //line above converts observable list into an array list
    

    if (alSelectedItems.size() < 2) { //show error if less than two strategies selected
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("At least two strategies must be selected");
      alert.showAndWait();
      return;
    }

    ArrayList<Integer> payoffs = new ArrayList<>();
    try { //checks payoff values are integers
      payoffs.add(Integer.parseInt(cc1.getText()));
      payoffs.add(Integer.parseInt(dc1.getText()));
      payoffs.add(Integer.parseInt(dc2.getText()));
      payoffs.add(Integer.parseInt(dd1.getText()));
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Payoff values must be integers");
      alert.showAndWait();
      return;
    }
    
    gameLengths = new ArrayList<>();
    
    try {
      if (gameLengths.size() != 0) {
        gameLengths.set(0, Integer.parseInt(game1.getText()));
        gameLengths.set(1, Integer.parseInt(game2.getText()));
        gameLengths.set(2, Integer.parseInt(game3.getText()));
      } else {
        gameLengths.add(0, Integer.parseInt(game1.getText()));
        gameLengths.add(1, Integer.parseInt(game2.getText()));
        gameLengths.add(2, Integer.parseInt(game3.getText()));
      }
    } catch (Exception e) {
      e.printStackTrace();
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Game lengths must be integers");
      alert.showAndWait();
      return;
    }
    int chosenRounds = 10;
    try {
      chosenRounds = Integer.parseInt(rounds.getText());
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Round length must be an integer");
      alert.showAndWait();
      return;
    }
    
    int one = Integer.parseInt(game1.getText());
    int two = Integer.parseInt(game2.getText());
    int three = Integer.parseInt(game3.getText());
    if ((one + two + three) != chosenRounds 
        && !roundsCheckBox.isSelected()) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Total of game lengths must equal number of rounds");
      alert.showAndWait();
      return;
    }
    
    try {
      if (roundsCheckBox.isSelected()) {
        gameLengths.clear();
        random = new Random();
        tournament = new RoundRobin(alSelectedItems, 
            random.nextInt(99) + 1, payoffs, gameLengths);
      } else if (gamesCheckBox.isSelected()) {
        gameLengths.clear();
        tournament = new RoundRobin(alSelectedItems, 
            chosenRounds, payoffs, gameLengths);
      } else {
        tournament = new RoundRobin(alSelectedItems, 
            chosenRounds, payoffs, gameLengths);
      }
      
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
    
  }
  
  @FXML
  private void launchEvolutionary() {
    ObservableList<Strategy> selectedItems = strategyTable.getSelectionModel().getSelectedItems();
    ArrayList<Strategy> alSelectedItems = new ArrayList<Strategy>(selectedItems);
    if (alSelectedItems.size() < 2) { //show error if less than two strategies selected
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("At least two strategies must be selected");
      alert.showAndWait();
      return;
    }
    ArrayList<Integer> payoffs = new ArrayList<>();
    try { //checks payoff values are integers
      payoffs.add(Integer.parseInt(cc1.getText()));
      payoffs.add(Integer.parseInt(dc1.getText()));
      payoffs.add(Integer.parseInt(dc2.getText()));
      payoffs.add(Integer.parseInt(dd1.getText()));
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Payoff values must be integers");
      alert.showAndWait();
      return;
    }
    
    gameLengths = new ArrayList<>();
    
    try {
      if (gameLengths.size() != 0) {
        gameLengths.set(0, Integer.parseInt(game1.getText()));
        gameLengths.set(1, Integer.parseInt(game2.getText()));
        gameLengths.set(2, Integer.parseInt(game3.getText()));
      } else {
        gameLengths.add(0, Integer.parseInt(game1.getText()));
        gameLengths.add(1, Integer.parseInt(game2.getText()));
        gameLengths.add(2, Integer.parseInt(game3.getText()));
      }
    } catch (Exception e) {
      e.printStackTrace();
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Game lengths must be integers");
      alert.showAndWait();
      return;
    }
    int chosenRounds = 10;
    try {
      chosenRounds = Integer.parseInt(rounds.getText());
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Round length must be an integer");
      alert.showAndWait();
      return;
    }
    
    int one = Integer.parseInt(game1.getText());
    int two = Integer.parseInt(game2.getText());
    int three = Integer.parseInt(game3.getText());
    if ((one + two + three) != chosenRounds 
        && !roundsCheckBox.isSelected()) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Total of game lengths must equal number of rounds");
      alert.showAndWait();
      return;
    }
    if (roundsCheckBox.isSelected()) {
      gameLengths.clear();
      random = new Random();
      chosenRounds = random.nextInt(99) + 1;
    } else if (gamesCheckBox.isSelected()) {
      gameLengths.clear();
    }
    
    mainn.showEvSettings(alSelectedItems, chosenRounds, payoffs, gameLengths);
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
    if (strat.probabilityProperty().getValue() != "0") {
      if(Double.parseDouble(newValue) <= 1) {
        if(Double.parseDouble(newValue) >= 0) {
          strat.setProbability(Double.parseDouble(newValue));
        } else {
          Alert alert = new Alert(AlertType.WARNING);
          alert.setTitle("Warning Dialog");
          alert.setContentText("Probability must be between 0 and 1");
          alert.showAndWait();
        }
      } else {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Probability must be between 0 and 1");
        alert.showAndWait();
      }
    } else {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Warning Dialog");
      alert.setContentText("Editing cells containing 0 will not impact the tournament");
      alert.showAndWait();
    }
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
    try {
      if (strat.roundsProperty().getValue() != "0") {
        if(Integer.parseInt(newValue) > 0) {
          strat.setRounds(Integer.parseInt(newValue));
        } else {
          Alert alert = new Alert(AlertType.WARNING);
          alert.setTitle("Warning Dialog");
          alert.setContentText("Rounds must be more than 0");
          alert.showAndWait();
        }
      } else {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Editing cells containing 0 will not impact the tournament");
        alert.showAndWait();
      }
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Warning Dialog");
      alert.setContentText("Number of rounds must be an integer");
      alert.showAndWait();
    }
  }
  
  @FXML
  private void roundsCheckBoxSelected() {
    if (roundsCheckBox.isSelected()) {
      rounds.setEditable(false);
      rounds.setOpacity(0.5);
      gamesCheckBox.setSelected(true);
      gamesCheckBoxSelected();
    } else {
      rounds.setEditable(true);
      rounds.setOpacity(1);
      gamesCheckBox.setSelected(false);
      gamesCheckBoxSelected();
    }
  }
  
  @FXML
  private void gamesCheckBoxSelected() {
    if (gamesCheckBox.isSelected()) {
      game1.setEditable(false);
      game2.setEditable(false);
      game3.setEditable(false);
      game1.setOpacity(0.5);
      game2.setOpacity(0.5);
      game3.setOpacity(0.5);
    } else {
      game1.setEditable(true);
      game2.setEditable(true);
      game3.setEditable(true);
      game1.setOpacity(1);
      game2.setOpacity(1);
      game3.setOpacity(1);
    }
  }
  
  @FXML
  private void resetPayoffs() {
    cc1.setText("3");
    cc2.setText("3");
    cd1.setText("0");
    cd2.setText("5");
    dc1.setText("5");
    dc2.setText("0");
    dd1.setText("1");
    dd2.setText("1");
  }
  
  @FXML
  private void helpSelected() {
    try {
      File pdfFile = new File("Documentation/UserGuide.pdf");
      if (pdfFile.exists()) {
          if (Desktop.isDesktopSupported()) {
              Desktop.getDesktop().open(pdfFile);
          } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Unable to open user guide");
            alert.showAndWait();
            return;
          }
      } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Unable to find user guide");
        alert.showAndWait();
        return;
      }
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Unable to open user guide");
      alert.showAndWait();
      return;
    }
  }
  
}

