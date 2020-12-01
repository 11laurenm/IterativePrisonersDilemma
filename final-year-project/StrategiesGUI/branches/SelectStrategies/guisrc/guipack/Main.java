package guipack;

import java.io.IOException;

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
import javafx.stage.Stage;
import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.Strategy;

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
        this.primaryStage.setTitle("AddressApp");
        
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public Main() {
		strategyData.add(new AlwaysCooperate());
		strategyData.add(new AlwaysDefect());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
