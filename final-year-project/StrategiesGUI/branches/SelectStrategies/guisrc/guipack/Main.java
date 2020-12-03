package guipack;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.Strategy;
import strategiespack.VaryingMajority;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Strategy> strategyData = FXCollections.observableArrayList();
	
	public ObservableList<Strategy> getStrategyData() {
		return strategyData;
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        
        initRootLayout();

        showOverview();
        
	}
	
	public void initRootLayout() {
        try {
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            ;
        }
    }

    public void showOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Overview.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();
            
            rootLayout.setCenter(Overview);

            Controller controller = loader.getController();
            controller.initialize(this);
            
        } catch (IOException e) {
            ;
        }
    }
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public Main() {
		strategyData.add(new AlwaysCooperate());
		strategyData.add(new AlwaysDefect());
		strategyData.add(new VaryingMajority(5));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void displayResults(ArrayList<Strategy> results) {
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("OutputResults.fxml"));
	        AnchorPane page;
			page = (AnchorPane) loader.load();
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Tournament results");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        ResultsController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setResults(results);
	        dialogStage.showAndWait();
		} catch (IOException e) {
			;
		}
	}
}
