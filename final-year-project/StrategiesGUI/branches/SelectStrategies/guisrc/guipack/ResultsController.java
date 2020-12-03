package guipack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import strategiespack.Strategy;

public class ResultsController {
	
	@FXML
    private TableView<Strategy> resultsTable;
    @FXML
    private TableColumn<Strategy, String> placeColumn;
    @FXML
    private TableColumn<Strategy, String> strategyColumn;
    @FXML
    private TableColumn<Strategy, String> pointsColumn;
	
    private ObservableList<Strategy> resultsList = FXCollections.observableArrayList();
    
	private Stage dialogStage;
	
	@FXML
    private void initialize() {
		resultsTable.setItems(resultsList);
		placeColumn.setCellValueFactory(new PropertyValueFactory<Strategy,String>("position"));
		strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy,String>("name"));
		pointsColumn.setCellValueFactory(new PropertyValueFactory<Strategy,String>("points"));
    }
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	public void setResults(ArrayList<Strategy> results) {
		resultsList = FXCollections.observableArrayList(results);
	}
	

}
