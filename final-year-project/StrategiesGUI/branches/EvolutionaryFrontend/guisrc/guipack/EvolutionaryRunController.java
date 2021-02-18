package guipack;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import strategiespack.Evolutionary;
import strategiespack.Node;
import strategiespack.Strategy;

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
  
  int mostRecentGen;
  
  @FXML
  Label generationLabel;
  
  int generationNumber;
  
  public EvolutionaryRunController() {
    
  }
  
  @FXML 
  public void initialize(ArrayList<Button> buttonsList, ArrayList<Node> nodesList) {
    buttons = buttonsList;
    nodes = nodesList;
    generationNumber = 0;
    mostRecentGen = 0;
    allGens = new ArrayList<ArrayList<Node>>();
    showButtons();
    ArrayList<Integer> payoffs = new ArrayList<>(Arrays.asList(3, 5, 0, 1));
    ArrayList<Integer> gameLengths = new ArrayList(Arrays.asList(1, 1, 1));
    evoTournament = new Evolutionary(nodes, 10, payoffs, gameLengths, 5);
    evoTournament.setUpTournament();
    allGens.add(nodes);
  }
  
  public void showButtons() {
    for(Button b: buttons) {
      buttonsPane.getChildren().add(b);
    }
  }
  
  public void nextGen() {
    if(generationNumber == mostRecentGen) {
      evoTournament.runGeneration();
      nodes = evoTournament.returnResults();
      allGens.add(nodes);
      mostRecentGen++;
    }
    generationNumber++;
    generationLabel.setText(Integer.toString(generationNumber));
    updateNodes();
  }
  
  public void previousGen() {
    if(generationNumber > 0) {
      generationNumber--;
      generationLabel.setText(Integer.toString(generationNumber));
    }
    updateNodes();
  }
  
  public void updateNodes() {
    ArrayList<Node> genNodes = allGens.get(generationNumber);
    for(int buttonNumber = 0; buttonNumber < buttons.size(); buttonNumber++) {
      Button b = buttons.get(buttonNumber);
      String buttonStyle = "-fx-background-color: " + genNodes.get(buttonNumber).getStrategy().colourProperty().get();
      b.setStyle(buttonStyle);
    }
  }

}
