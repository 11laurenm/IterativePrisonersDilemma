package guipack;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import strategiespack.AlwaysCooperate;
import strategiespack.AlwaysDefect;
import strategiespack.AlwaysRandom;
import strategiespack.Gradual;
import strategiespack.HardMajority;
import strategiespack.HardTitForTat;
import strategiespack.Mem2;
import strategiespack.Mistrust;
import strategiespack.Node;
import strategiespack.Pavlov;
import strategiespack.PeriodicCCD;
import strategiespack.PeriodicCD;
import strategiespack.PeriodicDDC;
import strategiespack.Prober;
import strategiespack.RoundRobin;
import strategiespack.ScoreBased;
import strategiespack.SoftMajority;
import strategiespack.Spiteful;
import strategiespack.Strategy;
import strategiespack.TitForTat;
import strategiespack.TitForTatWithForgiveness;
import strategiespack.TitForTwoTats;
import strategiespack.VaryingMajority;
import strategiespack.VaryingRandom;

/**
 * Main class used to create and launch GUI.

 * @author Lauren Moore - zfac043
 *     Some code adapted from https://code.makery.ch/library/javafx-tutorial/, author Marco Jakob
 */

public class Main extends Application {

  /**
   * Used to display GUI.
   */
  private Stage primaryStage;
  private BorderPane rootLayout;
  public Stage dialogStage;
  
  /**
   * List containing every possible strategy to display in the GUI.
   */
  private ObservableList<Strategy> strategyData = FXCollections.observableArrayList();

  /**
   * The tournament to be run.
   */
  public RoundRobin tournament;
  
  /**
   * Getter used to get the list containing every possible strategy.

   * @return an ObservableList containing every possible strategy
   */
  public ObservableList<Strategy> getStrategyData() {
    return strategyData;
  }

  /**
   * Sets up the stage and other objects so GUI can be displayed 
   * and runs the showOverview method.
   */
  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    primaryStage.setMinWidth(900);
    primaryStage.setMinHeight(500);
    initRootLayout();

    showOverview();  
  }

  /**
   * Sets up the root layout which contains menu items.
   */
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

  /**
   * Used to display the overview.fxml file as the main GUI, 
   * which allows the user to configure and run tournaments
   */
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
  
  /**
   * Used to display the EvolutionaryFirstScreen.fxml file as the main GUI, 
   * which allows the user to configure an evolutionary tournament.
   */
  public void showEvSettings(ArrayList<Strategy> strategies, int rounds, 
      ArrayList<Integer> payoffs, ArrayList<Integer> gameLengths) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("EvolutionaryFirstScreen.fxml"));
      AnchorPane overview = (AnchorPane) loader.load();
    
      rootLayout.setCenter(overview);

      EvolutionarySettingsController controller = loader.getController();
      controller.initialize(this, strategies, rounds, payoffs, gameLengths);
    
    } catch (IOException e) {
      ;
    }
  }
  
  /**
   * Used to display the EvolutionaryRunScreen.fxml file as the main GUI, 
   * which allows the user to run an evolutionary tournament.
   */
  public void showEvRun(ArrayList<Button> buttons, ArrayList<Node> nodes, 
      ArrayList<Strategy> strategiesForTable, int rounds, 
      ArrayList<Integer> payoffs, ArrayList<Integer> gameLengths, int generations) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("EvolutionaryRunScreen.fxml"));
      AnchorPane overview = (AnchorPane) loader.load();
    
      rootLayout.setCenter(overview);

      EvolutionaryRunController controller = loader.getController();
      controller.initialize(buttons, nodes, strategiesForTable, rounds, 
          payoffs, gameLengths, generations);
    
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * getter for the primary stage.

   * @return primary stage
   */
  public Stage getPrimaryStage() {
    return primaryStage;
  }

  /**
   * Constructor which creates the list of possible strategies.
   */
  public Main() {
    strategyData.add(new AlwaysCooperate());
    strategyData.add(new AlwaysDefect());
    strategyData.add(new AlwaysRandom());
    strategyData.add(new Gradual());
    strategyData.add(new HardMajority());
    strategyData.add(new HardTitForTat());
    strategyData.add(new Mem2());
    strategyData.add(new Mistrust());
    strategyData.add(new Pavlov());
    strategyData.add(new PeriodicCCD());
    strategyData.add(new PeriodicCD());
    strategyData.add(new PeriodicDDC());
    strategyData.add(new Prober());
    strategyData.add(new ScoreBased());
    strategyData.add(new SoftMajority());
    strategyData.add(new Spiteful());
    strategyData.add(new TitForTat());
    strategyData.add(new TitForTatWithForgiveness(0.5));
    strategyData.add(new TitForTwoTats());
    strategyData.add(new VaryingMajority(5));
    strategyData.add(new VaryingRandom(0.5));
  }

  /**
   * Main function which launches the program/GUI.

   * @param args - no CL args are needed
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Launches the results dialog once a tournament has been run.
   */
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

  /**
   * Closes the results dialog.
   */
  public void closeResults() {
    dialogStage.close();
  }

  /**
   * Launches the detailed results dialog.
   */
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
      controller.setResults(tournament.returnDecisions(), tournament.returnPoints(), 
              tournament.returnResults(), tournament.returnGameLengths());
      dialogStage.showAndWait();
    } catch (IOException e) {
      ;
    }
  }
  
  public void exportDetailedResults() {
    tournament.writeToCsv();
  }

  /**
   * Sets the value of the tournament variable.

   * @param tourn - a tournament
   */
  public void setTournament(RoundRobin tourn) {
    tournament = tourn;
  }

}
