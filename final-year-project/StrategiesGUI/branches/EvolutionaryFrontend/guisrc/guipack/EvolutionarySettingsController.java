package guipack;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import strategiespack.Strategy;

public class EvolutionarySettingsController {
  
  private Main mainn;
  
  @FXML
  private TableView<Strategy> stratsTable;
  
  @FXML
  private TableColumn<Strategy, String> strategyColumn;
  
  @FXML
  private TableColumn<Strategy, String> colourColumn;
  
  private ObservableList<Strategy> stratsList = FXCollections.observableArrayList();
  
  public EvolutionarySettingsController() {
    
  }
  
  @FXML 
  public void initialize(Main main, ArrayList<Strategy> strats) {
    setMain(main);
    stratsList = FXCollections.observableArrayList(strats);
    stratsTable.setItems(stratsList);
    strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("name"));
  }
  
  public void setMain(Main mainclass) {
    this.mainn = mainclass;
  }
  
  @FXML
  public void backButton() {
    mainn.showOverview();
  }

}
