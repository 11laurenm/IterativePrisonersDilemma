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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import strategiespack.Node;
import strategiespack.Strategy;

/**
 * Controller class used to handle the configure evolutionary tournament screen of GUI.

 * @author Lauren Moore - zfac043
 */

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
  private RadioButton pathGraphButton;
  
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
  
  @FXML
  private TextField generationField;
  
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
  
  /**
   * Empty constructor for the controller.
   */
  public EvolutionarySettingsController() {
    
  }
  
  /**
   *  Method that runs when screen is launched in order to initialise all necessary variables.

   * @param main - the Main class, used to launch other screens when needed.
   * @param strats - all the strategies selected by the user.
   * @param rounds - the total number of rounds selected by the user.
   * @param payoffs - the payoff values selected by the user.
   * @param gameLengths - the game lengths selected by the user.
   */
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
  
  /**
   * Method responsible for running the correct graph building method depending 
   * on which type of graph the user has selected via radio button.
   */
  @FXML
  public void runCorrectAnchorMethod() {
    setNodesPaneChildren();
    if (gridButton.isSelected()) {
      setSecondNodesPaneChildren();
      setAnchorGrid();
    } else if (circleGraphButton.isSelected()) {
      setAnchorCircle();
    } else if (starGraphButton.isSelected()) {
      setAnchorStar();
    } else if (pathGraphButton.isSelected()) {
      setAnchorPath();
    } else if (completeGraphButton.isSelected()) {
      setAnchorCircle();
    } else {
      setSecondNodesPaneChildren();
      setAnchorBipartite();
    }
  }
  
  /**
   * Method that creates the first text field for choosing the number of nodes and ensures that 
   * a new graph is generated when the user has changed the value in the text field.
   */
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
    
    if (gridButton.isSelected()) {
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
          setAnchorStar();
        } 
      }));
    } else if (pathGraphButton.isSelected()) {
      tf.setOnAction((new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent event) { 
          setAnchorPath();
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
  
  /**
   * Method that creates the second text field for choosing the number of nodes where needed
   *  and ensures that a new graph is generated when the user 
   *  has changed the value in the text field.
   */
  public void setSecondNodesPaneChildren() {
    setNodesPaneChildren();
    Label lab2 = new Label();
    lab2.setText("     X     ");
    lab2.setLayoutX(tf.getLayoutX() + tf.getPrefWidth() - 10);
    nodesPane.getChildren().add(lab2);
    
    tf2 = new TextField();
    tf2.setText(tfText2);
    tf2.setPrefWidth(25);
    tf2.setLayoutX(lab2.getLayoutX() + lab2.getPrefWidth() + 35);
    nodesPane.getChildren().add(tf2);
    
    if (gridButton.isSelected()) {
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
  
  /**
   * Method responsible for generating a graph when the user has chosen the grid option.
   */
  @FXML
  public void setAnchorGrid() {
    graphPane.getChildren().clear();
    buttons.clear();
    int rowSize = Integer.parseInt(tf.getText());;
    int colSize = Integer.parseInt(tf2.getText());;
    double height;
    double width;
    if (graphPane.getHeight() == 0.00) {
      height = graphPane.getMinHeight();
      width = graphPane.getMinWidth();
    } else {
      height = graphPane.getHeight();
      width = graphPane.getWidth();
    }
    minnWidth = width / (rowSize * 2);
    minnHeight = height / (colSize * 2);
    nodeHorizontalDistance = width / (rowSize + 2);
    nodeVerticalDistance = height / (colSize + 2);
    for (int colNum = 0; colNum < colSize; colNum++) {
      for (int rowNum = 0; rowNum < rowSize; rowNum++) {
        Button graphButton = new Button();
        graphButton.setMinSize(minnWidth, minnHeight);
        graphButton.setLayoutX((rowNum + 1) * nodeHorizontalDistance);
        graphButton.setLayoutY((colNum + 1) * nodeVerticalDistance);
        graphButton.setOnAction(buttonPressedChangeColour());
        graphButton.setId(String.valueOf(colNum) + String.valueOf(rowNum));
        buttons.add(graphButton);
        graphPane.getChildren().add(graphButton);
      }
    }
  }
  
  /**
   * Method responsible for assigning the correct neighbours to each node.
   */
  public void gridRun() {
    buttonToNode();
    for (int first = 0; first < nodes.size(); first++) {
      Node firstNode = nodes.get(first);
      int columnNumber1 = Integer.parseInt(String.valueOf(firstNode.getNodeId().charAt(0)));
      int rowNumber1 = Integer.parseInt(String.valueOf(firstNode.getNodeId().charAt(1)));
      for (int second = first; second < nodes.size(); second++) {
        Node secondNode = nodes.get(second);
        int columnNumber2 = Integer.parseInt(String.valueOf(secondNode.getNodeId().charAt(0)));
        int rowNumber2 = Integer.parseInt(String.valueOf(secondNode.getNodeId().charAt(1)));
        if ((columnNumber1 == columnNumber2 && (Math.abs(rowNumber1 - rowNumber2) <= 1)) 
            || (rowNumber1 == rowNumber2 && (Math.abs(columnNumber1 - columnNumber2) <= 1))) {
          if (firstNode != secondNode) {
            firstNode.addNeighbour(secondNode);
            secondNode.addNeighbour(firstNode);
          }
        }
      }
    }
    setButtonData();
  }
  
  /**
   * Method responsible for generating a graph when the user has chosen the 
   * circle or complete option.
   */
  @FXML
  public void setAnchorCircle() {
    graphPane.getChildren().clear();
    buttons.clear();
    int nodeNumber = Integer.parseInt(tf.getText());
    double height;
    double width;
    double circleVerticalNodes = 0;
    double circleHorizontalNodes = 0;
    if (graphPane.getHeight() == 0.00) {
      height = graphPane.getMinHeight();
      width = graphPane.getMinWidth();
    } else {
      height = graphPane.getHeight();
      width = graphPane.getWidth();
    }
    
    if(nodeNumber == 2) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX((width / 5));
      graphButton.setLayoutY(height/2);
      graphButton.setOnAction(buttonPressedChangeColour());
      int setId = 1;
      graphButton.setId(String.valueOf(setId));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
      Button graphButton2 = new Button();
      graphButton2.setMinSize(minnWidth, minnHeight);
      graphButton2.setLayoutX((width / 5) * 3);
      graphButton2.setLayoutY(height/2);
      graphButton2.setOnAction(buttonPressedChangeColour());
      int setId2 = 2;
      graphButton.setId(String.valueOf(setId2));
      buttons.add(graphButton2);
      graphPane.getChildren().add(graphButton2);
      return;
    }
    
    if ((nodeNumber % 2) == 0) {
      for (int n = 0; n < nodeNumber / 2; n++) {
        if (n % 2 == 0) {
          circleVerticalNodes = circleVerticalNodes + 2;
        } else {
          circleHorizontalNodes = circleHorizontalNodes + 2;
        }
      }
    } else {
      boolean addVertical = true;
      for (int n = 0; n < nodeNumber; n++) {
        if (n < 3) {
          circleHorizontalNodes++;
        } else if (n < 5) {
          circleVerticalNodes++;
        } else {
          if (addVertical) {
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
    
    
    minnWidth = width / (((Math.ceil(circleHorizontalNodes / 2)) + 2) * 2);
    minnHeight = height / (((Math.ceil(circleVerticalNodes / 2)) + 2) * 2);
    nodeHorizontalDistance = width / (6 + circleHorizontalNodes) + minnWidth;
    nodeVerticalDistance = (height / (6 + circleVerticalNodes)) + minnHeight;
    
    for (int nodeLoopNum = 0; nodeLoopNum < Math.floor(circleHorizontalNodes / 2); nodeLoopNum++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX(nodeHorizontalDistance * (nodeLoopNum + 1.25));
      graphButton.setLayoutY(nodeVerticalDistance);
      graphButton.setOnAction(buttonPressedChangeColour());
      int setId = (int) nodeLoopNum + 1;
      graphButton.setId(String.valueOf(setId));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
    
    for (int nodeLoopNum = 0; nodeLoopNum < circleVerticalNodes / 2; nodeLoopNum++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX(((Math.ceil(circleHorizontalNodes / 2)) + 4 
          + (Math.ceil((circleHorizontalNodes / 2)) - 1)) * (nodeHorizontalDistance - minnWidth));
      graphButton.setLayoutY((nodeVerticalDistance * (nodeLoopNum + 2)));
      graphButton.setOnAction(buttonPressedChangeColour());
      Button b = buttons.get(buttons.size() - 1);
      int setId = Integer.parseInt(b.getId()) + 1;
      graphButton.setId(String.valueOf(setId));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
    
    for (int nodeLoopNum = 0; nodeLoopNum < Math.ceil(circleHorizontalNodes / 2); nodeLoopNum++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX((nodeHorizontalDistance - minnWidth) 
          * (2 + (Math.ceil(circleHorizontalNodes) / 2) 
          - nodeLoopNum + (Math.ceil((circleHorizontalNodes) / 2) - 1) - nodeLoopNum));
      graphButton.setLayoutY(nodeVerticalDistance * (2 * (circleVerticalNodes / 2)));
      graphButton.setOnAction(buttonPressedChangeColour());
      Button b = buttons.get(buttons.size() - 1);
      int setId = Integer.parseInt(b.getId()) + 1;
      graphButton.setId(String.valueOf(setId));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
    
    for (int nodeLoopNum = 0; nodeLoopNum < circleVerticalNodes / 2; nodeLoopNum++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX((nodeHorizontalDistance - minnWidth));
      graphButton.setLayoutY(nodeVerticalDistance * (1 + (circleVerticalNodes / 2) - nodeLoopNum));
      graphButton.setOnAction(buttonPressedChangeColour());
      Button b = buttons.get(buttons.size() - 1);
      int setId = Integer.parseInt(b.getId()) + 1;
      graphButton.setId(String.valueOf(setId));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
  }
  
  /**
   * Method responsible for assigning the correct neighbours to each node.
   */
  public void circleRun() {
    buttonToNode();
    for (int first = 0; first < nodes.size(); first++) {
      Node firstNode = nodes.get(first);
      int id1 = Integer.parseInt(String.valueOf(firstNode.getNodeId()));
      for (int second = first; second < nodes.size(); second++) {
        Node secondNode = nodes.get(second);
        int id2 = Integer.parseInt(String.valueOf(secondNode.getNodeId()));
        if (Math.abs(id2 - id1) == 1 || (id1 == 1 && id2 == Integer.parseInt(tf.getText()))) { 
          if (firstNode != secondNode) {
            firstNode.addNeighbour(secondNode);
            secondNode.addNeighbour(firstNode);
          }
        }
      }
    }
    setButtonData();
  }
  
  /**
   * Method responsible for assigning the correct neighbours to each node.
   */
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
  
  /**
   * Method responsible for generating a graph when the user has chosen the star option.
   */
  public void setAnchorStar() {
    graphPane.getChildren().clear();
    buttons.clear();
    
    int nodeNumber = Integer.parseInt(tf.getText());;
    double height;
    double width;
    if (graphPane.getHeight() == 0.00) {
      height = graphPane.getMinHeight();
      width = graphPane.getMinWidth();
    } else {
      height = graphPane.getHeight();
      width = graphPane.getWidth();
    }
    
    minnWidth = width / ((nodeNumber - 1) * 2);
    minnHeight = height / 5;
    nodeHorizontalDistance = width / (nodeNumber + 2);
    nodeVerticalDistance = height / 5;
    for (int nodeLoopNum = 0; nodeLoopNum < nodeNumber - 1; nodeLoopNum++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX((nodeLoopNum + 1) * nodeHorizontalDistance);
      graphButton.setLayoutY(nodeVerticalDistance * 3);
      graphButton.setOnAction(buttonPressedChangeColour());
      graphButton.setId(String.valueOf(nodeLoopNum));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
    Button graphButton = new Button();
    graphButton.setMinSize(minnWidth, minnHeight);
    graphButton.setLayoutX(((nodeNumber) / 2) * nodeHorizontalDistance);
    graphButton.setLayoutY(nodeVerticalDistance);
    graphButton.setOnAction(buttonPressedChangeColour());
    graphButton.setId(String.valueOf(nodeNumber));
    buttons.add(graphButton);
    graphPane.getChildren().add(graphButton);
    
  }
  
  /**
   * Method responsible for generating a graph when the user has chosen the path option.
   */
  @FXML
  public void setAnchorPath() {
    graphPane.getChildren().clear();
    buttons.clear();
    int nodeNumber = Integer.parseInt(tf.getText());;
    double height;
    double width;
    if (graphPane.getHeight() == 0.00) {
      height = graphPane.getMinHeight();
      width = graphPane.getMinWidth();
    } else {
      height = graphPane.getHeight();
      width = graphPane.getWidth();
    }
    
    minnWidth = width / (nodeNumber * 2);
    minnHeight = height / 4;
    nodeHorizontalDistance = width / (nodeNumber + 2);
    nodeVerticalDistance = height / 2;
    for (int nodeLoopNum = 0; nodeLoopNum < nodeNumber; nodeLoopNum++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX((nodeLoopNum + 1) * nodeHorizontalDistance);
      graphButton.setLayoutY(nodeVerticalDistance);
      graphButton.setOnAction(buttonPressedChangeColour());
      graphButton.setId(String.valueOf(nodeLoopNum));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
  }
  
  /**
   * Method responsible for assigning the correct neighbours to each node.
   */
  public void pathRun() {
    buttonToNode();
    for (int first = 0; first < nodes.size(); first++) {
      Node firstNode = nodes.get(first);
      int nodeNumber1 = Integer.parseInt(firstNode.getNodeId());
      for (int second = first; second < nodes.size(); second++) {
        Node secondNode = nodes.get(second);
        int nodeNumber2 = Integer.parseInt(secondNode.getNodeId());
        if (Math.abs(nodeNumber1 - nodeNumber2) == 1) {
          firstNode.addNeighbour(secondNode);
          secondNode.addNeighbour(firstNode);
        }
      }
    }
    setButtonData();
  }
  
  /**
   * Method responsible for assigning the correct neighbours to each node.
   */
  public void completeRun() {
    buttonToNode();
    for (int first = 0; first < nodes.size(); first++) {
      Node firstNode = nodes.get(first);
      for (int second = first; second < nodes.size(); second++) {
        if (first != second) {
          Node secondNode = nodes.get(second);
          firstNode.addNeighbour(secondNode);
          secondNode.addNeighbour(firstNode);
        }
      }
    }
    setButtonData();
  }
  
  /**
   * Method responsible for generating a graph when the user has chosen the bipartite option.
   */
  @FXML
  public void setAnchorBipartite() {
    graphPane.getChildren().clear();
    buttons.clear();
    int nodesPerColumnA = Integer.parseInt(tf.getText());
    int nodesPerColumnB = Integer.parseInt(tf2.getText());
    int largestColumn;
    if (nodesPerColumnA > nodesPerColumnA) {
      largestColumn = nodesPerColumnA;
    } else {
      largestColumn = nodesPerColumnB;
    }
    double height;
    double width;
    if (graphPane.getHeight() == 0.00) {
      height = graphPane.getMinHeight();
      width = graphPane.getMinWidth();
    } else {
      height = graphPane.getHeight();
      width = graphPane.getWidth();
    }
    minnWidth = width / 5;
    minnHeight = height / ((largestColumn * 2) + 2);
    nodeHorizontalDistance = minnWidth; 
    nodeVerticalDistance = minnHeight; 
    for (int colNode = 1; colNode < nodesPerColumnA + 1; colNode++) {
      Button graphButton = new Button();
      graphButton.setMinSize(minnWidth, minnHeight);
      graphButton.setLayoutX(minnWidth);
      graphButton.setLayoutY(minnHeight * colNode * 2);
      graphButton.setOnAction(buttonPressedChangeColour());
      graphButton.setId(String.valueOf(0) + String.valueOf(colNode));
      buttons.add(graphButton);
      graphPane.getChildren().add(graphButton);
    }
    for (int colNode = 1; colNode < nodesPerColumnB + 1; colNode++) {
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
  
  /**
   * Method responsible for assigning the correct neighbours to each node.
   */
  public void bipartiteRun() {
    buttonToNode();
    for (int first = 0; first < nodes.size(); first++) {
      Node firstNode = nodes.get(first);
      int columnNumber1 = Integer.parseInt(String.valueOf(firstNode.getNodeId().charAt(0)));
      for (int second = first; second < nodes.size(); second++) {
        Node secondNode = nodes.get(second);
        int columnNumber2 = Integer.parseInt(String.valueOf(secondNode.getNodeId().charAt(0)));
        if (columnNumber1 != columnNumber2) {
          firstNode.addNeighbour(secondNode);
          secondNode.addNeighbour(firstNode);
        }
      }
    }
    setButtonData();
  }
  
  /**
   * Event handler that runs when a button is pressed, changing its colour to represent the 
   * strategy the user has decided to associate with it.

   * @return event handler
   */
  private EventHandler<ActionEvent> buttonPressedChangeColour() {
    return new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Strategy selectedStrat = null;
        try {
          selectedStrat = stratsTable.getSelectionModel()
              .getSelectedItem().getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
          e.printStackTrace();
        }
        String buttonStyle = "-fx-background-color: " + selectedStrat.colourProperty().get();
        Button b = ((Button) event.getSource());
        b.setUserData(selectedStrat);
        b.setStyle(buttonStyle);
      }
    };
  }
  
  /**
   * Creates the list of nodes to be used during the running of the tournament and 
   * adds the id from each button to the corresponding node.
   */
  public void buttonToNode() {
    nodes = new ArrayList<>();
    for (Button button : buttons) {
      Node buttonToNodeVar = new Node((Strategy) button.getUserData());
      buttonToNodeVar.setNodeId(button.getId().toString());
      nodes.add(buttonToNodeVar);
    }
  }
  
  /**
   * Sets the corresponding node as the user data stored in each button.
   */
  public void setButtonData() {
    for (int buttonNumber = 0; buttonNumber < buttons.size(); buttonNumber++) {
      Button b = buttons.get(buttonNumber);
      b.setUserData(nodes.get(buttonNumber));
    }
  }
  
  /**
   * Creates a list of only the strategies selected by the user so it can be displayed in 
   * the next screen.
   */
  public void setTableData() {
    strategiesForTable = new ArrayList<Strategy>();
    for (int buttonNumber = 0; buttonNumber < buttons.size(); buttonNumber++) {
      Button b = buttons.get(buttonNumber);
      Node n = (Node) b.getUserData();
      Strategy s;
      s = n.getStrategy();
      boolean inList = false;
      for (Strategy checkS : strategiesForTable) {
        if (s.getClass().equals(checkS.getClass())) {
          inList = true;
        }
      }
      if (!inList) {
        strategiesForTable.add(s);
      }
    }
  }
  
  /**
   * Method that ensures a graph has been generated and populated with strategies, 
   * then runs the run method corresponding to the type of graph chosen, then 
   * launches the next screen.
   */
  @FXML
  public void runButton() {
    if (tf.getText().equals("0") || tf2.getText().equals("0")) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Number of nodes cannot be zero");
      alert.showAndWait();
      return;
    }
    
    try {
      if (gridButton.isSelected()) {
        gridRun();
      } else if (circleGraphButton.isSelected()) {
        circleRun();
      } else if (starGraphButton.isSelected()) {
        starRun();
      } else if (pathGraphButton.isSelected()) {
        pathRun();
      } else if (completeGraphButton.isSelected()) {
        completeRun();
      } else {
        bipartiteRun();
      }
      setTableData();
      int gens = 1;
      try {
        gens = Integer.parseInt(generationField.getText());
      } catch (Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Number of generations must be an integer");
        alert.showAndWait();
        return;
      }
      mainn.showEvRun(buttons, nodes, strategiesForTable, roundsParam, 
          payoffsParam, gameLengthsParam, gens);
    } catch (Exception e) {
      e.printStackTrace();
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("All nodes must have a strategy");
      alert.showAndWait();
      return;
    }
  }
  
}
