package guipack;

import guipack.Main;
import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.Strategy;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
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
		//strategyColumn.setCellValueFactory(data -> data.getValue().nameProperty());
		strategyColumn.setCellValueFactory(new PropertyValueFactory<Strategy,String>("name"));
    }
	
	public void setMain(Main Mainclass) {
        this.mainn = Mainclass;
        strategyTable.setItems(mainn.getStrategyData());
    }

}
