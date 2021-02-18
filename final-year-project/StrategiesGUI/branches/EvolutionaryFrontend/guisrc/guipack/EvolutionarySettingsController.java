package guipack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import strategiespack.Node;
import strategiespack.Strategy;

public class EvolutionarySettingsController {
  
  private Main mainn;
  
  @FXML
  private ToggleGroup graphType;
  
  @FXML
  private RadioButton gridButton;
  
  @FXML
  private RadioButton circleGraphButton;
  
  @FXML
  private RadioButton starGraphButton;
  
  @FXML
  private RadioButton busGraphButton;
  
  @FXML
  private RadioButton completeGraphButton;
  
  @FXML
  private RadioButton bipartiteGraphButton;
  
  @FXML
  private TableView<Strategy> stratsTable;
  
  @FXML
  private TableColumn<Strategy, String> strategyColumn;
  
  @FXML
  private TableColumn<Strategy, String> colourColumn;
  
  private ObservableList<Strategy> stratsList = FXCollections.observableArrayList();
  
  @FXML
  private AnchorPane graphPane;
  
  @FXML
  private TextField nodeNumber;
  
  ArrayList<Button> buttons;
  
  ArrayList<Node> nodes;
  
  public EvolutionarySettingsController() {
    
  }
  
  @FXML 
  public void initialize(Main main, ArrayList<Strategy> strats) {
    setMain(main);
    setAnchorGrid();
    stratsList = FXCollections.observableArrayList(strats);
    stratsTable.setItems(stratsList);
    
    strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("name"));
    
    colourColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("colour"));
  }
  
  public void setMain(Main mainclass) {
    this.mainn = mainclass;
  }
  
  @FXML
  public void backButton() {
    mainn.showOverview();
  }
  
  @FXML
  public void runCorrectAnchorMethod() {
    if(gridButton.isSelected()) {
      setAnchorGrid();
    } else if (circleGraphButton.isSelected()) {
      setAnchorCircle();
    } else if (starGraphButton.isSelected()) {
      
    } else if (busGraphButton.isSelected()) {
      
    } else if (completeGraphButton.isSelected()) {
      
    } else {
      
    }
  }
  
  @FXML
  public void setAnchorGrid() {
    graphPane.getChildren().clear();
    int rowSize = 4;
    int colSize = 3;
    double height;
    double width;
    if(graphPane.getHeight() == 0.00) {
      height = graphPane.getMinHeight();
      width = graphPane.getMinWidth();
    } else {
      height = graphPane.getHeight();
      width = graphPane.getWidth();
    }
    buttons = new ArrayList<>();
    double minnWidth = width/(rowSize * 2);
    double minnHeight = height/(colSize * 2);
    double nodeHorizontalDistance = width/(rowSize + 2);
    double nodeVerticalDistance = height/(colSize + 2);
    for(int colNum = 0; colNum < colSize; colNum++) {
      for(int rowNum = 0; rowNum < rowSize; rowNum++) {
        Button graphButton = new Button();
        graphButton.setMinSize(minnWidth, minnHeight);
        graphButton.setLayoutX((rowNum+1) * nodeHorizontalDistance);
        graphButton.setLayoutY((colNum + 1) * nodeVerticalDistance);
        graphButton.setOnAction(buttonPressedChangeColour());
        graphButton.setId(String.valueOf(colNum) + String.valueOf(rowNum));
        buttons.add(graphButton);
        graphPane.getChildren().add(graphButton);
      }
    }
  }
  
  @FXML
  public void setAnchorCircle() {
    graphPane.getChildren().clear();
  }
  
  @FXML
  public void setAnchorStar() {
    graphPane.getChildren().clear();
  }
  
  @FXML
  public void setAnchorBus() {
    graphPane.getChildren().clear();
  }
  
  @FXML
  public void setAnchorComplete() {
    graphPane.getChildren().clear();
  }
  
  @FXML
  public void setAnchorBipartite() {
    graphPane.getChildren().clear();
  }
  
  private EventHandler<ActionEvent> buttonPressedChangeColour() {
    return new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent event) {
        Strategy selectedStrat = null;
        try {
          selectedStrat = stratsTable.getSelectionModel().getSelectedItem().getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
          e.printStackTrace();
        }
        String buttonStyle = "-fx-background-color: " + selectedStrat.colourProperty().get();
        Button b = ((Button)event.getSource());
        b.setUserData(selectedStrat);
        b.setStyle(buttonStyle);
      }
    };
  }
  
  @FXML
  public void runButton() {
    nodes = new ArrayList<>();
    for(Button button: buttons) {
      Node buttonToNode = new Node((Strategy) button.getUserData());
      buttonToNode.setID(button.getId().toString());
      nodes.add(buttonToNode);
    }
    
    for (int first = 0; first < nodes.size(); first++) {
      Node firstNode = nodes.get(first);
      int columnNumber1 = Integer.parseInt(String.valueOf(firstNode.getID().charAt(0)));
      int rowNumber1 = Integer.parseInt(String.valueOf(firstNode.getID().charAt(1)));
      for (int second = first; second < nodes.size(); second++) {
        Node secondNode = nodes.get(second);
        int columnNumber2 = Integer.parseInt(String.valueOf(secondNode.getID().charAt(0)));
        int rowNumber2 = Integer.parseInt(String.valueOf(secondNode.getID().charAt(1)));
        if((columnNumber1 == columnNumber2 && (Math.abs(rowNumber1 - rowNumber2) <= 1)) ||
            (rowNumber1 == rowNumber2 && (Math.abs(columnNumber1 - columnNumber2) <= 1))) {
          firstNode.addNeighbour(secondNode);
          secondNode.addNeighbour(firstNode);
        }
      }
    }
    
    for(int buttonNumber = 0; buttonNumber < buttons.size(); buttonNumber++) {
      Button b = buttons.get(buttonNumber);
      b.setUserData(nodes.get(buttonNumber));
    }
    
    mainn.showEvRun(buttons, nodes);
    
  }

}
