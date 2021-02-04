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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
  
  public EvolutionarySettingsController() {
    
  }
  
  @FXML 
  public void initialize(Main main, ArrayList<Strategy> strats) {
    setAnchorPane();
    setMain(main);
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
  public void setAnchorPane() {
    if(gridButton.isSelected()) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("GridGraph.fxml"));
      loader.setController(this);
      try {
        graphPane.setCenter(loader.load());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

}
