package guipack;

import java.util.ArrayList;
import java.util.List;

import guipack.Main;
import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.RoundRobin;
import strategiespack.Strategy;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
	@FXML
    private TableView<Strategy> strategyTable;
    @FXML
    private TableColumn<Strategy, String> strategyColumn;
	
	private Main mainn;
	
	public Controller(){

	}
	
	@FXML 
	public void initialize(Main main) {
		setMain(main);
		strategyTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy,String>("name"));
    }
	
	public void setMain(Main Mainclass) {
        this.mainn = Mainclass;
        strategyTable.setItems(mainn.getStrategyData());
    }
	
	@FXML
    private void handleRunTournament() {
		ObservableList selectedItems = strategyTable.getSelectionModel().getSelectedItems();
		ArrayList<Strategy> ALSelectedItems = new ArrayList<Strategy>(selectedItems);
		RoundRobin tournament = new RoundRobin(ALSelectedItems, 4);
		tournament.runTournament();
    }

}
