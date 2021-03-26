package guipack;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import strategiespack.Evolutionary;
import strategiespack.Node;
import strategiespack.Strategy;

/**
 * Controller class used to handle the run evolutionary tournament screen of GUI.

 * @author Lauren Moore - zfac043
 */

public class EvolutionaryRunController {
  
  /**
   * The buttons that make up the graph displayed using the GUI.
   */
  ArrayList<Button> buttons;
  
  /**
   * The nodes that make up the graph conceptually and are used to run the tournament.
   */
  ArrayList<Node> nodes;
  
  /**
   * The AnchorPane containing the graph.
   */
  @FXML
  AnchorPane buttonsPane;
  
  /**
   * Contains the state of each node during each generation.
   */
  ArrayList<ArrayList<Node>> allGens;
  
  /**
   * The tournament being run.
   */
  Evolutionary evoTournament;
  
  /**
   * The generation being displayed by the GUI.
   */
  int generationNumber;
  
  /**
   * Table containing all the strategies chosen by the user to play in the tournament.
   */
  @FXML
  private TableView<Strategy> stratsTable;
  
  /**
   * Column displaying the strategy's name.
   */
  @FXML
  private TableColumn<Strategy, String> strategyColumn;
  
  /**
   * Column displaying the colour associated with the strategy.
   */
  @FXML
  private TableColumn<Strategy, String> colourColumn;
  
  /**
   * The list of strategies used to populate the TableView.
   */
  private ObservableList<Strategy> stratsList = FXCollections.observableArrayList();
  
  /**
   * The total number of generations run by the tournament.
   */
  int totalGens;
  
  /**
   * The Text Field used to display and set the generation displayed on the graph.
   */
  @FXML
  private TextField chosenGenNumber;
  
  /**
   * Allows the program to access the main function.
   */
  Main mainn;
  
  /**
   * The data passed to the results screen, 
   * this list is saved so it can include a back button.
   * To relaunch the configuration screen the run screen must have a list of the strategies used in this screen.
   */
  int rounds;
  ArrayList<Integer> payoffs;
  ArrayList<Integer> gameLengths;
  ArrayList<Strategy> allStrategies;
  
  /**
   * Empty constructor for the controller.
   */
  public EvolutionaryRunController() {
    
  }
  
  /**
   * Method that runs when screen is launched in order to initialise all necessary variables.

   * @param buttonsList - an ArrayList containing every button created in the previous screen.
   * @param nodesList - an ArrayList containing every node created in the previous screen.
   * @param strats - all the strategies selected by the user.
   * @param setRounds - the total number of rounds selected by the user.
   * @param setPayoffs - the payoff values selected by the user.
   * @param setGameLengths - the game lengths selected by the user.
   * @param gens - the total number of generations selected by the user.
   */
  @FXML 
  public void initialize(Main main, ArrayList<Button> buttonsList, ArrayList<Node> nodesList, 
      ArrayList<Strategy> strats, ArrayList<Strategy> allStrats, int setRounds, 
      ArrayList<Integer> setPayoffs, ArrayList<Integer> setGameLengths, int gens) {
    buttons = buttonsList;
    nodes = nodesList;
    generationNumber = 0;
    chosenGenNumber.setText(Integer.toString(generationNumber));
    allGens = new ArrayList<ArrayList<Node>>();
    showButtons();
    totalGens = gens;
    rounds = setRounds;
    payoffs = setPayoffs;
    gameLengths = setGameLengths;
    allStrategies = allStrats;
    evoTournament = new Evolutionary(nodes, setRounds, setPayoffs, setGameLengths, totalGens);
    evoTournament.setUpTournament();
    evoTournament.runWholeTournament();
    allGens = evoTournament.returnAllGenerationResults();
    stratsList = FXCollections.observableArrayList(strats);
    stratsTable.setItems(stratsList);
    strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("name"));
    colourColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("colour"));
    setButtonHandlers();
    setMain(main);
  }
  
  /**
   * Sets the value of the main variable so the main class can be accessed and 
   * also sets which strategies should be displayed in the table.

   * @param mainclass 
   */
  public void setMain(Main mainclass) {
    this.mainn = mainclass;
  }
  
  /**
   * Displays the graph generated in the previous screen.
   */
  public void showButtons() {
    for (Button b : buttons) {
      buttonsPane.getChildren().add(b);
    }
  }
  
  /**
   * Runs the updateNodes method to show the results of the next generation 
   * of the tournament.
   */
  public void nextGen() {
    if (generationNumber == totalGens) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Reached total number of generations run");
      alert.showAndWait();
      return;
    }
    generationNumber++;
    chosenGenNumber.setText(Integer.toString(generationNumber));
    updateNodes();
  }
  
  /**
   * Runs the updateNodes method to show the results of the previous generation 
   * of the tournament.
   */
  public void previousGen() {
    if (generationNumber > 0) {
      generationNumber--;
      chosenGenNumber.setText(Integer.toString(generationNumber));
    }
    updateNodes();
  }
  
  /**
   * Runs the updateNodes method to show the results of the selected generation 
   * of the tournament.
   */
  public void setGen() {
    try {
      String oldNum = Integer.toString(generationNumber);
      generationNumber = Integer.parseInt(chosenGenNumber.getText());
      if (generationNumber > totalGens) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Generation number must be less than " + totalGens);
        alert.showAndWait();
        return;
      }
      if (generationNumber < 0) {
        chosenGenNumber.setText(oldNum);
        generationNumber = Integer.parseInt(oldNum);
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Number of generations must be a positive integer");
        alert.showAndWait();
        return;
      }
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Number of generations must be a positive integer");
      alert.showAndWait();
      return;
    }
    updateNodes();
  }
  
  /**
   * Updates the colour of the nodes in the graph to show the results of the selected
   * generation of the tournament.
   */
  public void updateNodes() {
    ArrayList<Node> genNodes = allGens.get(generationNumber);
    for (int buttonNumber = 0; buttonNumber < buttons.size(); buttonNumber++) {
      Button b = buttons.get(buttonNumber);
      String buttonStyle = "-fx-background-color: " 
          + genNodes.get(buttonNumber).getStrategy().colourProperty().get();
      b.setStyle(buttonStyle);
    }
  }
  
  /**
   * Event handler that prevents a button from changing colour due to being pressed.

   * @return event handler
   */
  private EventHandler<ActionEvent> newButtonPressedChangeColour() {
    return new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        return;
      }
    };
  }
  
  /**
   * Iterates through the list of buttons and changes their functionality (makes it so 
   * that the user can no longer interact with them).
   */
  public void setButtonHandlers() {
    for (Button b : buttons) {
      b.setOnAction(newButtonPressedChangeColour());
    }
  }
  
  /**
   * Used to re-launch the evolutionary tournament configuration screen.
   */
  public void backPressed() {
    mainn.showEvSettings(allStrategies, rounds, payoffs, gameLengths);
  }
}
