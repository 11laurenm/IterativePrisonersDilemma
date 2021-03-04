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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
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
  
  ArrayList<Strategy> strategiesForTable;
  
  ArrayList<Button> buttons;
  
  ArrayList<Node> nodes;
  
  String tfText1 = "4";
  String tfText2 = "4";
  
  double minnWidth;
  double minnHeight;
  double nodeHorizontalDistance;
  double nodeVerticalDistance;
  
  int roundsParam;
  ArrayList<Integer> payoffsParam;
  ArrayList<Integer> gameLengthsParam;
  
  
  public EvolutionarySettingsController() {
    
  }
  
  @FXML 
  public void initialize(Main main, ArrayList<Strategy> strats, int rounds, 
      ArrayList<Integer> payoffs, ArrayList<Integer> gameLengths) {
    roundsParam = rounds;
    payoffsParam = payoffs;
    gameLengthsParam = gameLengths;
    setMain(main);
    buttons = new ArrayList<>();
    runCorrectAnchorMethod();
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
    setNodesPaneChildren();
    if(gridButton.isSelected()) {
      setSecondNodesPaneChildren();
      setAnchorGrid();
    } else if (circleGraphButton.isSelected()) {
      setAnchorCircle();
    } else if (starGraphButton.isSelected()) {
      setAnchorCircle();
      setAnchorStar();
    } else if (busGraphButton.isSelected()) {
      setAnchorBus();
    } else if (completeGraphButton.isSelected()) {
      setAnchorCircle();
    } else {
      setSecondNodesPaneChildren();
      setAnchorBipartite();
    }
  }
  
  public void setNodesPaneChildren() {
    nodesPane.getChildren().clear();
    Label lab = new Label();
    lab.setText("Number of Nodes");
    lab.setLayoutX(90);
    nodesPane.getChildren().add(lab);
    
    tf = new TextField();
    tf.setText(tfText1);
    tf.setPrefWidth(25);
    tf.setLayoutX(lab.getLayoutX() + lab.getPrefWidth() + 100);
    nodesPane.getChildren().add(tf);
    
    if(gridButton.isSelected()) {
      tf.setOnAction((new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent event) { 
           setAnchorGrid();
        } 
       }));
    } else if (circleGraphButton.isSelected()) {
      tf.setOnAction((new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent event) { 
           setAnchorCircle();
        } 
       }));
    } else if (starGraphButton.isSelected()) {
      tf.setOnAction((new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent event) { 
           setAnchorCircle();
           setAnchorStar();
        } 
       }));
    } else if (busGraphButton.isSelected()) {
      tf.setOnAction((new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent event) { 
           setAnchorBus();
        } 
       }));
    } else if (completeGraphButton.isSelected()) {
      tf.setOnAction((new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent event) { 
           setAnchorCircle();
        } 
       }));
    } else {
      tf.setOnAction((new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent event) { 
           setAnchorBipartite();
        } 
       }));
    }
  }
  
  public void setSecondNodesPaneChildren() {
    setNodesPaneChildren();
    Label lab2 = new Label();
    lab2.setText("     X     ");
    lab2.setLayoutX(tf.getLayoutX() + tf.getPrefWidth() - 10);
    nodesPane.getChildren().add(lab2);
    
    tf2 = new TextField();
    tf2.setText(tfText2);
    tf2.setPrefWidth(25);
    tf2.setLayoutX(lab2.getLayoutX()+ lab2.getPrefWidth() + 35);
    nodesPane.getChildren().add(tf2);
    
    if(gridButton.isSelected()) {
      tf2.setOnAction((new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent event) { 
           setAnchorGrid();
        } 
       }));
    } else {
      tf2.setOnAction((new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent event) { 
           setAnchorBipartite();
        } 
       }));
    }
  }
  
  @FXML
  public void setAnchorGrid() {
    graphPane.getChildren().clear();
    buttons.clear();
    int rowSize = Integer.parseInt(tf.getText());;
    int colSize = Integer.parseInt(tf2.getText());;
    double height;
    double width;
    if(graphPane.getHeight() == 0.00) {
      height = graphPane.getMinHeight();
      width = graphPane.getMinWidth();
    } else {
      height = graphPane.getHeight();
      width = graphPane.getWidth();
    }
    minnWidth = width/(rowSize * 2);
    minnHeight = height/(colSize * 2);
    nodeHorizontalDistance = width/(rowSize + 2);
    nodeVerticalDistance = height/(colSize + 2);
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
          if(firstNode != secondNode) {
            firstNode.addNeighbour(secondNode);
            secondNode.addNeighbour(firstNode);
          }
        }
      }
    }
    setButtonData();
  }
  
  @FXML
  public void setAnchorCircle() {
    graphPane.getChildren().clear();
    buttons.clear();
    int nodeNumber = Integer.parseInt(tf.getText());
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
    
    
    minnWidth = width/(((Math.ceil(circleHorizontalNodes / 2)) + 2) * 2);
    minnHeight = height/(((Math.ceil(circleVerticalNodes / 2)) + 2) * 2);
    nodeHorizontalDistance = width/(6 + circleHorizontalNodes) + minnWidth;
    nodeVerticalDistance = (height/(6 + circleVerticalNodes)) + minnHeight;
    
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
      Button b = buttons.get(buttons.size() - 1);
      int setID = Integer.parseInt(b.getId()) + 1;
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
      Button b = buttons.get(buttons.size() - 1);
      int setID = Integer.parseInt(b.getId()) + 1;
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
      Button b = buttons.get(buttons.size() - 1);
      int setID = Integer.parseInt(b.getId()) + 1;
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
        if(Math.abs(id2 - id1) == 1 || (id1 == 1 && id2 == Integer.parseInt(tf.getText()))){ 
          if(firstNode != secondNode) {
            firstNode.addNeighbour(secondNode);
            secondNode.addNeighbour(firstNode);
          }
        }
      }
    }
    setButtonData();
  }
  
  public void starRun() {
    buttonToNode();
    Node middleNode = nodes.get(nodes.size() - 1);
    for (int add = 0; add < nodes.size() - 1; add++) {
      Node otherNode = nodes.get(add);
      otherNode.addNeighbour(middleNode);
      middleNode.addNeighbour(otherNode);
    }
    setButtonData();
  }
  
  public void setAnchorStar() {
    
    int nodeNumber = Integer.parseInt(tf.getText());
    
    Button graphButton = new Button();
    Button firstButton = buttons.get(0);
    graphButton.setMinSize(minnWidth, minnHeight);
    graphButton.setLayoutX(nodeHorizontalDistance * (nodeNumber/4));
    graphButton.setLayoutY(nodeVerticalDistance * (nodeNumber/2));
    graphButton.setOnAction(buttonPressedChangeColour());
    graphButton.setId(String.valueOf(-1));
    buttons.add(graphButton);
    graphPane.getChildren().add(graphButton);
  }
  
  @FXML
  public void setAnchorBus() {
    graphPane.getChildren().clear();
    buttons.clear();
    int nodeNumber = Integer.parseInt(tf.getText());;
    double height;
    double width;
    if(graphPane.getHeight() == 0.00) {
      height = graphPane.getMinHeight();
      width = graphPane.getMinWidth();
    } else {
      height = graphPane.getHeight();
      width = graphPane.getWidth();
    }
    
    minnWidth = width/(nodeNumber * 2);
    minnHeight = height/4;
    nodeHorizontalDistance = width/(nodeNumber + 2);
    nodeVerticalDistance = height/2;
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
        if(first != second) {
          Node secondNode = nodes.get(second);
          firstNode.addNeighbour(secondNode);
          secondNode.addNeighbour(firstNode);
        }
      }
    }
    setButtonData();
  }
  
  @FXML
  public void setAnchorBipartite() {
    graphPane.getChildren().clear();
    buttons.clear();
    int nodesPerColumnA = Integer.parseInt(tf.getText());
    int nodesPerColumnB = Integer.parseInt(tf2.getText());
    int largestColumn;
    if(nodesPerColumnA > nodesPerColumnA) {
      largestColumn = nodesPerColumnA;
    } else {
      largestColumn = nodesPerColumnB;
    }
    double height;
    double width;
    if(graphPane.getHeight() == 0.00) {
      height = graphPane.getMinHeight();
      width = graphPane.getMinWidth();
    } else {
      height = graphPane.getHeight();
      width = graphPane.getWidth();
    }
    minnWidth = width/5;
    minnHeight = height/((largestColumn * 2) + 2);
    nodeHorizontalDistance = minnWidth; 
    nodeVerticalDistance = minnHeight; 
    for(int colNode = 1; colNode < nodesPerColumnA + 1; colNode++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX(minnWidth);
      graphButton.setLayoutY(minnHeight * colNode * 2);
      graphButton.setOnAction(buttonPressedChangeColour());
      graphButton.setId(String.valueOf(0) + String.valueOf(colNode));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
    for(int colNode = 1; colNode < nodesPerColumnB + 1; colNode++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX(3 * minnWidth);
      graphButton.setLayoutY(minnHeight * colNode * 2);
      graphButton.setOnAction(buttonPressedChangeColour());
      graphButton.setId(String.valueOf(1) + String.valueOf(colNode));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
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
  
  public void setTableData() {
    strategiesForTable = new ArrayList<Strategy>();
    for(int buttonNumber = 0; buttonNumber < buttons.size(); buttonNumber++) {
      Button b = buttons.get(buttonNumber);
      Node n = (Node) b.getUserData();
      Strategy s;
      s = n.getStrategy();
      boolean inList = false;
      for(Strategy checkS: strategiesForTable) {
        if(s.getClass().equals(checkS.getClass())) {
          inList = true;
        }
      }
      if(!inList) {
        strategiesForTable.add(s);
      }
    }
  }
  
  @FXML
  public void runButton() {
    if(tf.getText().equals("0") || tf2.getText().equals("0")) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Number of nodes cannot be zero");
      alert.showAndWait();
      return;
    }
    
    try {
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
      setTableData();
      mainn.showEvRun(buttons, nodes, strategiesForTable, roundsParam, payoffsParam, gameLengthsParam);
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("All nodes must have a strategy");
      alert.showAndWait();
      return;
    }
  }

}
