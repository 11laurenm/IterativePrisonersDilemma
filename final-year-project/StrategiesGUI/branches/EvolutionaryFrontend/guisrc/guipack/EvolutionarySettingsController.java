package guipack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
  private BorderPane graphPane;
  
  private String toLoad;
  
  @FXML
  private GridPane gridGraph;
  
  @FXML
  private TextField rows;
  
  @FXML
  private TextField cols;
  
  public EvolutionarySettingsController() {
    
  }
  
  @FXML 
  public void initialize(Main main, ArrayList<Strategy> strats) {
    setBorderPane();
    setMain(main);
    gridResize();
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
  public void setBorderPane() {
    if(gridButton.isSelected()) {
      toLoad = "Grid";
    } else if (circleGraphButton.isSelected()) {
      toLoad = "Circle";
    } else if (starGraphButton.isSelected()) {
      toLoad = "Star";
    } else if (completeGraphButton.isSelected()) {
      toLoad = "Complete";
    } else if (bipartiteGraphButton.isSelected()) {
      toLoad = "Bipartite";
    } else if (busGraphButton.isSelected()) {
      toLoad = "Bus";
    }
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource(toLoad + "Graph.fxml"));
    loader.setController(this);
    try {
      graphPane.setCenter(loader.load());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @FXML
  public void gridSquareSelected() {
    
  }
  
  @FXML
  public void gridResize() {
    int totalRows = Integer.parseInt(rows.getText());
    int totalCols = Integer.parseInt(cols.getText());
    int currentRows = gridGraph.getRowConstraints().size();
    int currentCols = gridGraph.getColumnConstraints().size();
    
    if(totalRows < currentRows) {
      for(int i = 0; i < currentRows - totalRows; i++) {
        gridGraph.getRowConstraints().remove(currentRows - 1);
      }
    } else if(totalCols < currentCols) {
      for(int i = 0; i < currentCols - totalCols; i++) {
        gridGraph.getColumnConstraints().remove(currentCols - 1);
      }
    } else if(totalRows > currentRows) {
      for(int i = 0; i < totalRows - currentRows; i++) {
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(100 / (totalRows));
        gridGraph.getRowConstraints().add(row1);
      }
    } else {
      for(int i = 0; i < totalCols - currentCols; i++) {
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(100 / (totalCols));
        gridGraph.getColumnConstraints().add(col1);
      }
    }
    
  }

}