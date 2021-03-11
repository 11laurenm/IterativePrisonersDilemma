package guipack;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
  
  ArrayList<Button> buttons;
  ArrayList<Node> nodes;
  @FXML
  AnchorPane buttonsPane;
  
  @FXML
  Button nextButton;
  
  @FXML
  Button previousButton;
  
  ArrayList<ArrayList<Node>> allGens;
  
  Evolutionary evoTournament;
  
  int generationNumber;
  
  @FXML
  private TableView<Strategy> stratsTable;
  
  @FXML
  private TableColumn<Strategy, String> strategyColumn;
  
  @FXML
  private TableColumn<Strategy, String> colourColumn;
  
  private ObservableList<Strategy> stratsList = FXCollections.observableArrayList();
  
  int totalGens;
  
  @FXML
  private TextField chosenGenNumber;
  
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
   * @param rounds - the total number of rounds selected by the user.
   * @param payoffs - the payoff values selected by the user.
   * @param gameLengths - the game lengths selected by the user.
   * @param gens - the total number of generations selected by the user.
   */
  @FXML 
  public void initialize(ArrayList<Button> buttonsList, ArrayList<Node> nodesList, 
      ArrayList<Strategy> strats, int rounds, 
      ArrayList<Integer> payoffs, ArrayList<Integer> gameLengths, int gens) {
    buttons = buttonsList;
    nodes = nodesList;
    generationNumber = 0;
    chosenGenNumber.setText(Integer.toString(generationNumber));
    allGens = new ArrayList<ArrayList<Node>>();
    showButtons();
    totalGens = gens;
    evoTournament = new Evolutionary(nodes, rounds, payoffs, gameLengths, totalGens);
    evoTournament.setUpTournament();
    evoTournament.runWholeTournament();
    allGens = evoTournament.returnAllGenerationResults();
    stratsList = FXCollections.observableArrayList(strats);
    stratsTable.setItems(stratsList);
    strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("name"));
    colourColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("colour"));
  }
  
  public void showButtons() {
    for (Button b : buttons) {
      buttonsPane.getChildren().add(b);
    }
  }
  
  public void nextGen() {
    generationNumber++;
    chosenGenNumber.setText(Integer.toString(generationNumber));
    updateNodes();
  }
  
  public void previousGen() {
    if (generationNumber > 0) {
      generationNumber--;
      chosenGenNumber.setText(Integer.toString(generationNumber));
    }
    updateNodes();
  }
  
  public void setGen() {
    try {
      generationNumber = Integer.parseInt(chosenGenNumber.getText());
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Number of generations must be an integer");
      alert.showAndWait();
      return;
    }
    updateNodes();
  }
  
  public void updateNodes() {
    ArrayList<Node> genNodes = allGens.get(generationNumber);
    for (int buttonNumber = 0; buttonNumber < buttons.size(); buttonNumber++) {
      Button b = buttons.get(buttonNumber);
      String buttonStyle = "-fx-background-color: " 
          + genNodes.get(buttonNumber).getStrategy().colourProperty().get();
      b.setStyle(buttonStyle);
    }
  }
}
