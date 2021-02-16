package guipack;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
  
  public EvolutionarySettingsController() {
    
  }
  
  @FXML 
  public void initialize(Main main, ArrayList<Strategy> strats) {
    setMain(main);
    setAnchorGrid();
    stratsList = FXCollections.observableArrayList(strats);
    stratsTable.setItems(stratsList);
    
    ArrayList<String> colours = new ArrayList<String>(
        Arrays.asList("Red", "Green", "Blue", "Yellow", "Pink", "White", "Black", "Purple", "Light Grey", 
            "Orange", "Light Blue", "Light Green", "Brown", "Navy", "Dark Grey", "Lilac", "Teal", "Lime", 
            "Light Pink", "Peach", "Mustard"));
    
    strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("name"));
    
    colourColumn.setCellValueFactory(new Callback<CellDataFeatures<Strategy, String>, 
        ObservableValue<String>>() {
      @Override public ObservableValue<String> call(CellDataFeatures<Strategy, String> p) {
        return new ReadOnlyObjectWrapper(colours.get(stratsTable.getItems().indexOf(p.getValue())));
      }
    });
  }
  
  public void setMain(Main mainclass) {
    this.mainn = mainclass;
  }
  
  @FXML
  public void backButton() {
    mainn.showOverview();
  }
  
  @FXML
  public void setAnchorGrid() {
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
    ArrayList<Button> buttons = new ArrayList<>();
    double minnWidth = width/(rowSize * 2);
    double minnHeight = height/(colSize * 2);
    double nodeHorizontalDistance = width/(rowSize + 2);
    double nodeVerticalDistance = height/(colSize + 2);
    for(int j = 0; j < colSize; j++) {
      for(int i = 0; i < rowSize; i++) {
        Button graphButton = new Button();
        graphButton.setMinSize(minnWidth, minnHeight);
        graphButton.setLayoutX((i+1) * nodeHorizontalDistance);
        graphButton.setLayoutY((j + 1) * nodeVerticalDistance);
        graphButton.setOnAction(buttonPressedChangeColour());
        buttons.add(graphButton);
        graphPane.getChildren().add(graphButton);
      }
    }
  }
  
  private EventHandler<ActionEvent> buttonPressedChangeColour() {
    return null;
  }

}
