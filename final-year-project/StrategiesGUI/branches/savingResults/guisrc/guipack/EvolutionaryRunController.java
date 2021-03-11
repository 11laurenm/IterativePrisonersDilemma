package guipack;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
  
  public EvolutionaryRunController() {
    
  }
  
  @FXML 
  public void initialize(ArrayList<Button> buttonsList, ArrayList<Node> nodesList, ArrayList<Strategy> strats, int rounds, 
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
    for(Button b: buttons) {
      buttonsPane.getChildren().add(b);
    }
  }
  
  public void nextGen() {
    generationNumber++;
    chosenGenNumber.setText(Integer.toString(generationNumber));
    updateNodes();
  }
  
  public void previousGen() {
    if(generationNumber > 0) {
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
    for(int buttonNumber = 0; buttonNumber < buttons.size(); buttonNumber++) {
      Button b = buttons.get(buttonNumber);
      String buttonStyle = "-fx-background-color: " + genNodes.get(buttonNumber).getStrategy().colourProperty().get();
      b.setStyle(buttonStyle);
    }
  }
  
  

}
