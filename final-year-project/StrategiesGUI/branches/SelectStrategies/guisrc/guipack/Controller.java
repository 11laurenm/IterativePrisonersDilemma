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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
	@FXML
    private TableView<Strategy> strategyTable;
    @FXML
    private TableColumn<Strategy, String> strategyColumn;
	
    @FXML
    private TextField CC1;
    @FXML
    private TextField CC2;
    @FXML
    private TextField CD1;
    @FXML
    private TextField CD2;
    @FXML
    private TextField DC1;
    @FXML
    private TextField DC2;
    @FXML
    private TextField DD1;
    @FXML
    private TextField DD2;
    
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
		
		ArrayList<Integer> payoffs = new ArrayList<>();
		payoffs.add(Integer.parseInt(CC1.getText()));
		payoffs.add(Integer.parseInt(DC1.getText()));
		payoffs.add(Integer.parseInt(DC2.getText()));
		payoffs.add(Integer.parseInt(DD1.getText()));
		
		RoundRobin tournament = new RoundRobin(ALSelectedItems, 4, payoffs);
		tournament.runTournament();
    }
	
	@FXML
	private void CC1Edited() {
		CC2.setText(CC1.getText());
	}
	
	@FXML
	private void CC2Edited() {
		CC1.setText(CC2.getText());
	}
	
	@FXML
	private void CD1Edited() {
		DC2.setText(CD1.getText());
	}
	
	@FXML
	private void CD2Edited() {
		DC1.setText(CD2.getText());
	}
	
	@FXML
	private void DC1Edited() {
		CD2.setText(DC1.getText());
	}
	
	@FXML
	private void DC2Edited() {
		CD1.setText(DC2.getText());
	}
	
	@FXML
	private void DD1Edited() {
		DD2.setText(DD1.getText());
	}
	
	@FXML
	private void DD2Edited() {
		DD1.setText(DD2.getText());
	}

}
