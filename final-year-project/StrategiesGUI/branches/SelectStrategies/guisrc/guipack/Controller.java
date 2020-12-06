package guipack;

import guipack.Main;
import java.util.ArrayList;
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
import strategiespack.RoundRobin;
import strategiespack.Strategy;

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
  private TextField cc1;
  @FXML
  private TextField cc2;
  @FXML
  private TextField cd1;
  @FXML
  private TextField cd2;
  @FXML
  private TextField dc1;
  @FXML
  private TextField dc2;
  @FXML
  private TextField dd1;
  @FXML
  private TextField dd2;
  @FXML
  private TextField rounds;

  private Main mainn;
	
  public Controller(){
  }

  @FXML 
  public void initialize(Main main) {
    setMain(main);
    strategyTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("name"));
    probabilityColumn.setCellValueFactory(new PropertyValueFactory<Strategy, 
            String>("probability"));
    probabilityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    roundsColumn.setCellValueFactory(new PropertyValueFactory<Strategy, String>("rounds"));
    roundsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
  }
	
  public void setMain(Main mainclass) {
    this.mainn = mainclass;
    strategyTable.setItems(mainn.getStrategyData());
  }
	
  @FXML
  private void handleRunTournament() {
    ObservableList selectedItems = strategyTable.getSelectionModel().getSelectedItems();
    ArrayList<Strategy> alSelectedItems = new ArrayList<Strategy>(selectedItems);

    ArrayList<Integer> payoffs = new ArrayList<>();

    if (alSelectedItems.size() < 2) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("At least two strategies must be selected");
      alert.showAndWait();
      return;
    }

    try {
      payoffs.add(Integer.parseInt(cc1.getText()));
      payoffs.add(Integer.parseInt(dc1.getText()));
      payoffs.add(Integer.parseInt(dc2.getText()));
      payoffs.add(Integer.parseInt(dd1.getText()));
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText("Payoff values must be integers");
      alert.showAndWait();
    }
    try {
      RoundRobin tournament = new RoundRobin(alSelectedItems, 
              Integer.parseInt(rounds.getText()), payoffs);
      try {
        tournament.runTournament();
        mainn.setTournament(tournament);
        mainn.displayResults();
      } catch (Exception e) {
        e.printStackTrace();
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
  private void cc1Edited() {
    cc2.setText(cc1.getText());
  }

  @FXML
  private void cc2Edited() {
    cc1.setText(cc2.getText());
  }

  @FXML
  private void cd1Edited() {
    dc2.setText(cd1.getText());
  }

  @FXML
  private void cd2Edited() {
    dc1.setText(cd2.getText());
  }

  @FXML
  private void dc1Edited() {
    cd2.setText(dc1.getText());
  }

  @FXML
  private void dc2Edited() {
    cd1.setText(dc2.getText());
  }

  @FXML
  private void dd1Edited() {
    dd2.setText(dd1.getText());
  }

  @FXML
  private void dd2Edited() {
    dd1.setText(dd2.getText());
  }

  @FXML
  private void probabilityEdited(CellEditEvent<Strategy, String> event) {
    String newValue = event.getNewValue();
    Strategy strat = event.getRowValue();
    strat.setProbability(Integer.parseInt(newValue));
  }

  @FXML
  private void roundsEdited(CellEditEvent<Strategy, String> event) {
    String newValue = event.getNewValue();
    Strategy strat = event.getRowValue();
    strat.setRounds(Integer.parseInt(newValue));
  }
}

