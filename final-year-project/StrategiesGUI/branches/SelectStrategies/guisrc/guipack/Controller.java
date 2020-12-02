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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class Controller {
	@FXML
    private TableView<Strategy> strategyTable;
    @FXML
    private TableColumn<Strategy, String> strategyColumn;
    @FXML
    private TableColumn<Strategy, String> probabilityColumn;
    @FXML
    private TableColumn<Strategy, String> roundsColumn;
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
    @FXML
    private TextField Rounds;
    
	private Main mainn;
	
	public Controller(){

	}
	
	@FXML 
	public void initialize(Main main) {
		setMain(main);
		strategyTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy,String>("name"));
		probabilityColumn.setCellValueFactory(new PropertyValueFactory<Strategy,String>("probability"));
		probabilityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		roundsColumn.setCellValueFactory(new PropertyValueFactory<Strategy,String>("rounds"));
		roundsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
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
		try {
			payoffs.add(Integer.parseInt(CC1.getText()));
			payoffs.add(Integer.parseInt(DC1.getText()));
			payoffs.add(Integer.parseInt(DC2.getText()));
			payoffs.add(Integer.parseInt(DD1.getText()));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setContentText("Payoff values must be integers");
			alert.showAndWait();
		}
		try {
			RoundRobin tournament = new RoundRobin(ALSelectedItems, Integer.parseInt(Rounds.getText()), payoffs);
			try {
				tournament.runTournament();
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setContentText("Error running tournament");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setContentText("Round length must be an integer");
			alert.showAndWait();
		}
		
		
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
	
	@FXML
	private void probabilityEdited() {
		;
	}
	
	@FXML
	private void roundsEdited(CellEditEvent<Strategy,String> event) {
	        String newValue = event.getNewValue();
	        Strategy strat = event.getRowValue();
	        strat.setRounds(Integer.parseInt(newValue));
	}
}

