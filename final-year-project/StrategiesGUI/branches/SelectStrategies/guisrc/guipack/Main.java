package guipack;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.AlwaysRandom;
import strategiespack.HardMajority;
import strategiespack.Mistrust;
import strategiespack.Pavlov;
import strategiespack.RoundRobin;
import strategiespack.SoftMajority;
import strategiespack.Spiteful;
import strategiespack.Strategy;
import strategiespack.TitForTat;
import strategiespack.VaryingMajority;
import strategiespack.VaryingRandom;

public class Main extends Application {

  private Stage primaryStage;
  private BorderPane rootLayout;
  private ObservableList<Strategy> strategyData = FXCollections.observableArrayList();

  public Stage dialogStage;
  public RoundRobin tournament;
  
  public ObservableList<Strategy> getStrategyData() {
    return strategyData;
  }

  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    primaryStage.setMinWidth(900);
    primaryStage.setMinHeight(500);
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
      AnchorPane overview = (AnchorPane) loader.load();
    
      rootLayout.setCenter(overview);

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
    strategyData.add(new AlwaysRandom());
    strategyData.add(new HardMajority());
    strategyData.add(new Mistrust());
    strategyData.add(new Pavlov());
    strategyData.add(new SoftMajority());
    strategyData.add(new Spiteful());
    strategyData.add(new TitForTat());
    strategyData.add(new VaryingRandom(0.5));
  }

  public static void main(String[] args) {
    launch(args);
  }

  public void displayResults() {
    ArrayList<Strategy> results = tournament.returnResults();
    ArrayList<Integer> scores = tournament.returnScores();
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("OutputResults.fxml"));
      AnchorPane page;
      page = (AnchorPane) loader.load();
      dialogStage = new Stage();
      dialogStage.setMinWidth(900);
      dialogStage.setMinHeight(500);
      dialogStage.setTitle("Tournament results");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(primaryStage);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);
      ResultsController controller = loader.getController();
      controller.constructGrid(results, scores);
      controller.setResults(results);
      controller.initialize();
      controller.setMain(this);
      dialogStage.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void closeResults() {
    dialogStage.close();
  }

  public void launchDetailedResults() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("OutputDetailedResults.fxml"));
      AnchorPane page;
      page = (AnchorPane) loader.load();
      dialogStage = new Stage();
      dialogStage.setTitle("Detailed Tournament Results");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.initOwner(primaryStage);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);
      DetailedResultsController controller = loader.getController();
      controller.initialize();
      controller.setMain(this);
      controller.setResults(tournament.returnDecisions(), tournament.returnPoints(), 
              tournament.returnResults(), tournament.returnGameLengths());
      dialogStage.showAndWait();
    } catch (IOException e) {
      ;
    }
  }

  public void setTournament(RoundRobin tourn) {
    tournament = tourn;
  }
}
