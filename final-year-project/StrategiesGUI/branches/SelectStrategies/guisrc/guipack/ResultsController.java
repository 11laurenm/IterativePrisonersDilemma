package guipack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    @FXML
    private GridPane resultsGrid;
	
    private Main mainn;
    
    private ObservableList<Strategy> resultsList = FXCollections.observableArrayList();
    
	
	@FXML void initialize() {
		resultsTable.setItems(resultsList);

		placeColumn.setCellValueFactory(new Callback<CellDataFeatures<Strategy, String>, ObservableValue<String>>() {
			  @Override public ObservableValue<String> call(CellDataFeatures<Strategy, String> p) {
			    return new ReadOnlyObjectWrapper(resultsTable.getItems().indexOf(p.getValue()) + 1);
			  }
		});  
		strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy,String>("name"));
		pointsColumn.setCellValueFactory(new PropertyValueFactory<Strategy,String>("points"));
    }
	
	public void setResults(ArrayList<Strategy> results) {
		ArrayList<Strategy> sortedResults = new ArrayList<>();
		while(results.size() > 0) {
			int highestpos = 0;
			for(int i = 0; i < results.size(); i++) {
				if(results.get(i).getPoints() > results.get(highestpos).getPoints()) {
					highestpos = i;
				}
			}
			sortedResults.add(results.get(highestpos));
			results.remove(highestpos);
		}
		resultsList = FXCollections.observableArrayList(sortedResults);
	}
	
	public void setMain(Main Mainclass) {
        this.mainn = Mainclass;
    }
	
	@FXML
    private void handleBackButton() {
		mainn.closeResults();
	}
	
	@FXML
	private void detailedResultsButton() {
		mainn.launchDetailedResults();
	}
	
	void constructGrid(ArrayList<Strategy> results, ArrayList<Integer> scores) {
		
		for(int i = 0; i < results.size(); i++) {
			Label label = new Label();
			label.setText(results.get(i).nameProperty());
		}
		
		int pos = 0;
		for(int i = 0; i < results.size(); i++) {
			for(int j = i; j < results.size(); j++) {
				Label label = new Label();
				label.setText(Integer.toString(scores.get(pos)) + ", " + Integer.toString(scores.get(pos + 1)));
				resultsGrid.add(label, i, j);
				if(i != j) {
					Label label2 = new Label();
					label2.setText(Integer.toString(scores.get(pos + 1)) + ", " + Integer.toString(scores.get(pos)));
					resultsGrid.add(label2, j, i);
				}
				pos = pos + 2;
			}
		}
	}
}
