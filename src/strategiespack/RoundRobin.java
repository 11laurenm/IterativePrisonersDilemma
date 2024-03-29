package strategiespack;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The class representing a tournament of the Iterative Prisoner's Dilemma.

 * @author Lauren Moore - zfac043
 *      code for creating a CSV file adapted from: https://stackabuse.com/reading-and-writing-csvs-in-java/, author Jean Fernando
 *
 */

public class RoundRobin extends Tournament {

  /**
   * All strategies participating in the tournament.
   */
  ArrayList<Strategy> strategies;
  
  /**
   * Constructor for the class, which sets the following variables to values 
   * chosen by the user.

   * @param strats - the strategies participating in the tournament
   * @param rounds - the total number of rounds they will play
   * @param payoffs - the scores earned for each combination of decisions
   * @param gameLengths - the length of each of the three games to be played
   */
  public RoundRobin(ArrayList<Strategy> strats, int rounds, ArrayList<Integer> payoffs, 
      ArrayList<Integer> gameLengths) {
    totalRounds = rounds;
    strategies = strats;
    payoffList = payoffs;
    scores = new ArrayList<>();
    lengths = new ArrayList<>();
    setGameLengths = gameLengths;
  }
        
  /**
   * The method responsible for running the tournament which decides 
   * how many rounds each of the three games should be, then
   * iterates through the list of strategies to run 3 games with every possible pairing.
   */
  public void runTournament() {
    
    setUpTournament();

    decisions = new ArrayList<ArrayList<Character>>();
    points = new ArrayList<ArrayList<Integer>>();

    if (setGameLengths.size() == 0) {
      VaryGameLength varyLength = new VaryGameLength(totalRounds);
      first = varyLength.getFirstSet();
      second = varyLength.getSecondSet();
      third = varyLength.getThirdSet();
    } else {
      first = setGameLengths.get(0);
      second = setGameLengths.get(1);
      third = setGameLengths.get(2);
    }

    lengths.add(first); //save the length of each game so GUI can output it
    lengths.add(second);
    lengths.add(third);

    for (int i = 0; i < strategies.size(); i++) {
      for (int j = i; j < strategies.size(); j++) { //iterate through every pair of strategies
        player1Score = 0; //initialise each player's score for this pairing to 0
        player2Score = 0;
        Game game1 = new Game(strategies.get(i), strategies.get(j), first, payoffList);
        game1.playGame();
        endOfGame(game1);
        Game game2 = new Game(strategies.get(i), strategies.get(j), second, payoffList);
        game2.playGame();
        endOfGame(game2);
        Game game3 = new Game(strategies.get(i), strategies.get(j), third, payoffList);
        game3.playGame();
        endOfGame(game3);
        scores.add(player1Score);
        scores.add(player2Score);
      }
    }
  }
  
  /**
   * Method run before the main tournament, ensures each strategy starts with a score of 0.
   */
  public void setUpTournament() {
    for (int i = 0; i < strategies.size(); i++) {
      Strategy strat = strategies.get(i);
      strat.setPoints(0);
    }
  }
  
  /**
   * Returns the results of the tournament as points are 
   * associated with strategies.

   * @return all strategies in the tournament
   */
  public ArrayList<Strategy> returnResults() {
    return strategies;
  }
  
  /**
   * Method that writes the results of the tournament (including decisions made) 
   * to a csv file.
   */
  public void writeToCsv() {
    int pairNumber = 0;
    ArrayList<String> pairings = new ArrayList<>();
    ArrayList<ArrayList<String>> dataToWrite = new ArrayList<ArrayList<String>>();
   
    for (int i = 0; i < 3; i++) {
      ArrayList<String> data = new ArrayList<>(Arrays.asList(
          "", "Length of game " + (i + 1), Integer.toString(returnGameLengths().get(i))));
      dataToWrite.add(data);
    }
    ArrayList<String> spaceData = new ArrayList<>();
    spaceData.add(" ");
    dataToWrite.add(spaceData);

    ArrayList<String> titleData = new ArrayList<>(Arrays.asList(
        "Final Position", 
        "Strategy", 
        "Total Points"));
    dataToWrite.add(titleData);
    
    for (int i = 0; i < strategies.size(); i++) {
      ArrayList<String> resultsData = new ArrayList<>(Arrays.asList(
          Integer.toString(i + 1), strategies.get(i).nameProperty().getValue(), 
          Integer.toString(strategies.get(i).getPoints())));
      dataToWrite.add(resultsData);
    }
    dataToWrite.add(spaceData);
    
    
    for (int i = 0; i < strategies.size(); i++) {
      for (int j = i; j < strategies.size(); j++) { //creates list of every pairing
        pairings.add(strategies.get(i).nameProperty().getValue());
        pairings.add(strategies.get(j).nameProperty().getValue());
      }
    }
    
    for (int set = 0; set < decisions.size(); set = set + 3) {
      ArrayList<String> data = new ArrayList<>(Arrays.asList(
          "", pairings.get(pairNumber) + " Decision",
          pairings.get(pairNumber) + " Score", pairings.get(pairNumber + 1) + " Decision",
          pairings.get(pairNumber + 1) + " Score"));
      dataToWrite.add(data);
      pairNumber = pairNumber + 2;
      int roundNumber = 0;
      for (int j = 0; j < 3; j++) {
        ArrayList<Character> decisionsSet = returnDecisions().get(set + j);
        ArrayList<Integer> pointsSet = returnPoints().get(set + j);
        for (int i = 0; i < decisionsSet.size(); i = i + 2) {
          roundNumber++;
          ArrayList<String> data2 = new ArrayList<>(Arrays.asList(
              "Round " + Integer.toString(roundNumber), 
              Character.toString(decisionsSet.get(i)),
              Integer.toString(pointsSet.get(i)), 
              Character.toString(decisionsSet.get(i + 1)), 
              Integer.toString(pointsSet.get(i + 1))));
          dataToWrite.add(data2);
        }
      }
      dataToWrite.add(spaceData);
    }
    
    try {
      FileWriter csvWriter = new FileWriter("RoundRobinResults.csv");
      for (List<String> rowData : dataToWrite) {
        csvWriter.append(String.join(",", rowData));
        csvWriter.append("\n");
      }
      csvWriter.flush();
      csvWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}