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
import javafx.scene.control.Label;
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
  private AnchorPane nodesPane;
  
  @FXML
  private TextField tf;
  
  @FXML
  private TextField tf2;
  
  ArrayList<Button> buttons;
  
  ArrayList<Node> nodes;
  
  public EvolutionarySettingsController() {
    
  }
  
  @FXML 
  public void initialize(Main main, ArrayList<Strategy> strats) {
    setMain(main);
    buttons = new ArrayList<>();
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
      setAnchorCircle();
    } else if (busGraphButton.isSelected()) {
      setAnchorBus();
    } else if (completeGraphButton.isSelected()) {
      setAnchorCircle();
    } else {
      setAnchorBipartite();
    }
  }
  
  @FXML
  public void setAnchorGrid() {
    
    Label lab = new Label();
    lab.setText("Number of Nodes");
    lab.setLayoutX(90);
    nodesPane.getChildren().add(lab);
    
    tf = new TextField();
    tf.setText("4");
    tf.setPrefWidth(25);
    tf.setLayoutX(lab.getLayoutX() + lab.getPrefWidth() + 100);
    nodesPane.getChildren().add(tf);
    
    Label lab2 = new Label();
    lab2.setText("     X     ");
    lab2.setLayoutX(tf.getLayoutX() + tf.getPrefWidth() - 10);
    nodesPane.getChildren().add(lab2);
    
    tf2 = new TextField();
    tf2.setText("4");
    tf2.setPrefWidth(25);
    tf2.setLayoutX(lab2.getLayoutX()+ lab2.getPrefWidth() + 35);
    nodesPane.getChildren().add(tf2);
    
    graphPane.getChildren().clear();
    buttons.clear();
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
  
  public void gridRun() {
    buttonToNode();
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
    setButtonData();
  }
  
  @FXML
  public void setAnchorCircle() {
    graphPane.getChildren().clear();
    buttons.clear();
    int nodeNumber = 3;
    double height;
    double width;
    double circleVerticalNodes = 0;
    double circleHorizontalNodes = 0;
    if(graphPane.getHeight() == 0.00) {
      height = graphPane.getMinHeight();
      width = graphPane.getMinWidth();
    } else {
      height = graphPane.getHeight();
      width = graphPane.getWidth();
    }
    
    if((nodeNumber % 2) == 0) {
      for(int n = 0; n < nodeNumber/2; n++) {
        if(n % 2 == 0) {
          circleVerticalNodes = circleVerticalNodes + 2;
        } else {
          circleHorizontalNodes = circleHorizontalNodes + 2;
        }
      }
    } else {
      boolean addVertical = true;
      for(int n = 0; n < nodeNumber; n++) {
        if(n < 3) {
          circleHorizontalNodes++;
        } else if (n < 5) {
          circleVerticalNodes++;
        } else {
          if(addVertical) {
            circleVerticalNodes = circleVerticalNodes + 2;
            n++;
            addVertical = false;
          } else {
            circleHorizontalNodes = circleHorizontalNodes + 2;
            n++;
            addVertical = true;
          }
        }
      }
    }
    
    
    double minnWidth = width/(((Math.ceil(circleHorizontalNodes / 2)) + 2) * 2);
    double minnHeight = height/(((Math.ceil(circleVerticalNodes / 2)) + 2) * 2);
    double nodeHorizontalDistance = width/(6 + circleHorizontalNodes) + minnWidth;
    double nodeVerticalDistance = (height/(6 + circleVerticalNodes)) + minnHeight;
    
    for(int nodeLoopNum = 0; nodeLoopNum < Math.floor(circleHorizontalNodes/2); nodeLoopNum++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX(nodeHorizontalDistance * (nodeLoopNum + 1.25));
      graphButton.setLayoutY(nodeVerticalDistance);
      graphButton.setOnAction(buttonPressedChangeColour());
      int setID = (int) nodeLoopNum + 1;
      graphButton.setId(String.valueOf(setID));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
    
    for(int nodeLoopNum = 0; nodeLoopNum < circleVerticalNodes/2; nodeLoopNum++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX(((Math.ceil(circleHorizontalNodes/2)) + 4 + 
          (Math.ceil((circleHorizontalNodes/2)) - 1)) * (nodeHorizontalDistance - minnWidth));
      graphButton.setLayoutY((nodeVerticalDistance * (nodeLoopNum + 2)));
      graphButton.setOnAction(buttonPressedChangeColour());
      int setID = (int) (nodeLoopNum + circleHorizontalNodes/2 + 1);
      graphButton.setId(String.valueOf(setID));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
    
    for(int nodeLoopNum = 0; nodeLoopNum < Math.ceil(circleHorizontalNodes/2); nodeLoopNum++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX((nodeHorizontalDistance - minnWidth) * (2 + (Math.ceil(circleHorizontalNodes)/2) 
          - nodeLoopNum + (Math.ceil((circleHorizontalNodes)/2) - 1) - nodeLoopNum));
      graphButton.setLayoutY(nodeVerticalDistance * (2 * (circleVerticalNodes/2)));
      graphButton.setOnAction(buttonPressedChangeColour());
      int setID = (int) (nodeNumber - circleVerticalNodes/2 + nodeLoopNum - 1);
      graphButton.setId(String.valueOf(setID));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
    
    for(int nodeLoopNum = 0; nodeLoopNum < circleVerticalNodes/2; nodeLoopNum++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX((nodeHorizontalDistance - minnWidth));
      graphButton.setLayoutY(nodeVerticalDistance * (1 + (circleVerticalNodes/2) - nodeLoopNum));
      graphButton.setOnAction(buttonPressedChangeColour());
      int setID = (int) (nodeNumber - circleVerticalNodes/2 + nodeLoopNum + 1);
      graphButton.setId(String.valueOf(setID));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
  }
  
  public void circleRun() {
    buttonToNode();
    for (int first = 0; first < nodes.size(); first++) {
      Node firstNode = nodes.get(first);
      int id1 = Integer.parseInt(String.valueOf(firstNode.getID()));
      for (int second = first; second < nodes.size(); second++) {
        Node secondNode = nodes.get(second);
        int id2 = Integer.parseInt(String.valueOf(secondNode.getID()));
        if(Math.abs(id2 - id1) == 1){ //TODO add or nodeNumber and 1
          firstNode.addNeighbour(secondNode);
          secondNode.addNeighbour(firstNode);
        }
      }
    }
    setButtonData();
  }
  
  public void starRun() {
    buttonToNode();
    for (int first = 0; first < nodes.size(); first++) {
      Node firstNode = nodes.get(first);
      int id1 = Integer.parseInt(String.valueOf(firstNode.getID()));
      for (int second = first; second < nodes.size(); second++) {
        Node secondNode = nodes.get(second);
        int id2 = Integer.parseInt(String.valueOf(secondNode.getID()));
        if(!(Math.abs(id2 - id1) == 1)){ //TODO add or nodeNumber and 1
          firstNode.addNeighbour(secondNode);
          secondNode.addNeighbour(firstNode);
        }
      }
    }
    setButtonData();
  }
  
  @FXML
  public void setAnchorBus() {
    graphPane.getChildren().clear();
    buttons.clear();
    int nodeNumber = 3;
    double height;
    double width;
    if(graphPane.getHeight() == 0.00) {
      height = graphPane.getMinHeight();
      width = graphPane.getMinWidth();
    } else {
      height = graphPane.getHeight();
      width = graphPane.getWidth();
    }
    
    double minnWidth = width/(nodeNumber * 2);
    double minnHeight = height/4;
    double nodeHorizontalDistance = width/(nodeNumber + 2);
    double nodeVerticalDistance = height/2;
    for(int nodeLoopNum = 0; nodeLoopNum < nodeNumber; nodeLoopNum++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX((nodeLoopNum+1) * nodeHorizontalDistance);
      graphButton.setLayoutY(nodeVerticalDistance);
      graphButton.setOnAction(buttonPressedChangeColour());
      graphButton.setId(String.valueOf(nodeLoopNum));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
  }
  
  public void busRun() {
    buttonToNode();
    for (int first = 0; first < nodes.size(); first++) {
      Node firstNode = nodes.get(first);
      int nodeNumber1 = Integer.parseInt(firstNode.getID());
      for (int second = first; second < nodes.size(); second++) {
        Node secondNode = nodes.get(second);
        int nodeNumber2 = Integer.parseInt(secondNode.getID());
        if(Math.abs(nodeNumber1 - nodeNumber2) == 1) {
          System.out.println("NEIGHBOUR IDENTIFIED");
          firstNode.addNeighbour(secondNode);
          secondNode.addNeighbour(firstNode);
        }
      }
    }
    setButtonData();
  }
  

  
  public void completeRun() {
    buttonToNode();
    for (int first = 0; first < nodes.size(); first++) {
      Node firstNode = nodes.get(first);
      for (int second = first; second < nodes.size(); second++) {
        Node secondNode = nodes.get(second);
        firstNode.addNeighbour(secondNode);
        secondNode.addNeighbour(firstNode); 
      }
    }
    setButtonData();
  }
  
  @FXML
  public void setAnchorBipartite() {
    graphPane.getChildren().clear();
    buttons.clear();
    int nodesPerColumn = 3;
    double height;
    double width;
    if(graphPane.getHeight() == 0.00) {
      height = graphPane.getMinHeight();
      width = graphPane.getMinWidth();
    } else {
      height = graphPane.getHeight();
      width = graphPane.getWidth();
    }
    double minnWidth = width/5;
    double minnHeight = height/((nodesPerColumn * 2) + 2);
    double nodeHorizontalDistance = minnWidth; 
    double nodeVerticalDistance = minnHeight; 
    for(int colNumber = 0; colNumber < 2; colNumber++) {
      for(int colNode = 1; colNode < nodesPerColumn + 1; colNode++) {
        Button graphButton = new Button();
        graphButton.setMinSize(minnWidth, minnHeight);
        graphButton.setLayoutX(((colNumber * 2) + 1) * minnWidth);
        graphButton.setLayoutY(minnHeight * colNode * 2);
        graphButton.setOnAction(buttonPressedChangeColour());
        graphButton.setId(String.valueOf(colNumber) + String.valueOf(colNode));
        buttons.add(graphButton);
        graphPane.getChildren().add(graphButton);
      }
    }
  }
  
  public void bipartiteRun() {
    buttonToNode();
    for (int first = 0; first < nodes.size(); first++) {
      Node firstNode = nodes.get(first);
      int columnNumber1 = Integer.parseInt(String.valueOf(firstNode.getID().charAt(0)));
      for (int second = first; second < nodes.size(); second++) {
        Node secondNode = nodes.get(second);
        int columnNumber2 = Integer.parseInt(String.valueOf(secondNode.getID().charAt(0)));
        if(columnNumber1 != columnNumber2){
          firstNode.addNeighbour(secondNode);
          secondNode.addNeighbour(firstNode);
        }
      }
    }
    setButtonData();
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
  
  public void buttonToNode() {
    nodes = new ArrayList<>();
    for(Button button: buttons) {
      Node buttonToNodeVar = new Node((Strategy) button.getUserData());
      buttonToNodeVar.setID(button.getId().toString());
      nodes.add(buttonToNodeVar);
    }
  }
  
  public void setButtonData() {
    for(int buttonNumber = 0; buttonNumber < buttons.size(); buttonNumber++) {
      Button b = buttons.get(buttonNumber);
      b.setUserData(nodes.get(buttonNumber));
    }
  }
  
  @FXML
  public void runButton() {
    if(gridButton.isSelected()) {
      gridRun();
    } else if (circleGraphButton.isSelected()) {
      circleRun();
    } else if (starGraphButton.isSelected()) {
      starRun();
    } else if (busGraphButton.isSelected()) {
      busRun();
    } else if (completeGraphButton.isSelected()) {
      completeRun();
    } else {
      bipartiteRun();
    }
    mainn.showEvRun(buttons, nodes);
  }

}
