package strategiespack;

import java.util.ArrayList;

/**
 * The class representing an evolutionary tournament of the Iterative Prisoner's Dilemma.

 * @author Lauren Moore - zfac043
 *
 */

public class Evolutionary extends Tournament {
  
  /**
   * Every node represents a player in the tournament.
   */
  ArrayList<Node> nodes;
  
  /**
   * Stores the score of each node in the generation currently being run.
   */
  ArrayList<Integer> genScores;
  
  /**
   * The total number of generations to be run.
   */
  int generations;
  
  /**
   * An ArrayList of ArrayLists, each representing a generation 
   * and showing the state of each node at that point.
   */
  public ArrayList<ArrayList<Node>> allGenerations;
  
  /**
   * Constructor for the class, which sets the following variables to values 
   * chosen by the user.

   * @param nodeList - every node to be entered in the tournament
   * @param rounds - the total number of rounds they will play
   * @param payoffs - the scores earned for each combination of decisions
   * @param gameLengths - the length of each of the three games to be played
   * @param gens - the total number of generations the tournament will run
   */
  public Evolutionary(ArrayList<Node> nodeList, int rounds, ArrayList<Integer> payoffs, 
      ArrayList<Integer> gameLengths, int gens) {
    totalRounds = rounds;
    nodes = nodeList;
    genScores = new ArrayList<Integer>(nodes.size());
    setGameLengths = gameLengths;
    payoffList = payoffs;
    generations = gens;
    allGenerations = new ArrayList<ArrayList<Node>>();
    ArrayList<Node> newNodes = new ArrayList<>();
    for (Node n : nodes) {
      Node newNode = new Node(n.getStrategy());
      newNodes.add(newNode);
    }
    allGenerations.add(newNodes);
  }
  
  /**
   * Sets up the tournament by initialising the needed variables.
   */
  public void setUpTournament() {
    
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
  }
  
  /**
   * Method responsible for running the whole tournament by running the other methods 
   * that make up the tournament for each generation.
   */
  public void runWholeTournament() {
    for (int gen = 0; gen < generations; gen++) {
      runGeneration();
      ArrayList<Node> newNodes = new ArrayList<>();
      //it is necessary to create copies of the nodes each time to save their strategy and score
      for (Node n : nodes) {
        Node newNode = new Node(n.getStrategy());
        newNodes.add(newNode);
      }
      allGenerations.add(newNodes);
    }
  }
  
  /**
   * Runs a single generation of the tournament, then calls the relevant methods to save 
   * the results.
   */
  public void runGeneration() {
    for (int nodeNumber = 0; nodeNumber < nodes.size(); nodeNumber++) {
      Node currentNode = nodes.get(nodeNumber);
      currentNode.getStrategy().setPoints(0); 
      //initialise each player's score for this generation to 0
      currentNode.setPlayedAllGames(false);
    }
    
    for (int nodeNumber = 0; nodeNumber < nodes.size(); nodeNumber++) {
      Node currentNode = nodes.get(nodeNumber);
      for (int neighbourNumber = 0; neighbourNumber < currentNode.getNeighbours().size();
          neighbourNumber++) {
        Node neighbourNode = currentNode.neighbours.get(neighbourNumber);
        player1Score = 0; //initialise each player's score for this pairing to 0
        player2Score = 0;
        if (!neighbourNode.getPlayedAllGames() && !currentNode.getPlayedAllGames()) {
          Game game1 = new Game(currentNode.getStrategy(), neighbourNode.getStrategy(), 
              first, payoffList);
          game1.playGame();
          endOfGame(game1);
          Game game2 = new Game(currentNode.getStrategy(), neighbourNode.getStrategy(), 
              second, payoffList);
          game2.playGame();
          endOfGame(game2);
          Game game3 = new Game(currentNode.getStrategy(), neighbourNode.getStrategy(), 
              third, payoffList);
          game3.playGame();
          endOfGame(game3);
        }
      }
      nodes.get(nodeNumber).setPlayedAllGames(true);
    }
    normaliseScores();
    setGenerationScores(); //saves the scores earned by each node in this generation
    updateNodes();
  }
  
  /**
   * Updates each node with its new strategy if it has been taken over by a more successful 
   * neighbour.
   */
  public void updateNodes() {
    for (Node n : nodes) {
      Node highestScoring = n;
      for (Node neighbour : n.neighbours) { //identifies which neighbour has the highest score
        if (neighbour.getStrategy().getPoints() > highestScoring.getStrategy().getPoints()) {
          highestScoring = neighbour;
        }
      }
      try { //updates the strategy of the node to the strategy of its highest scoring neighbour
        Strategy strat = highestScoring.getStrategy().getClass().newInstance();
        strat.setProbability(Double.parseDouble(highestScoring.getStrategy()
            .probabilityProperty().get()));
        strat.setRounds(Integer.parseInt(highestScoring.getStrategy().roundsProperty().get()));
        n.newStrategy = strat;
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    for (Node n : nodes) {
      n.strategy = n.newStrategy;
    }
  }
  
  public ArrayList<Node> returnResults() {
    return nodes;
  }
  
  /**
   * Sets the list of scores for the current generation to the most recent scores.
   */
  public void setGenerationScores() {
    int score;
    genScores.clear();
    for (int n = 0; n < nodes.size(); n++) {
      score = nodes.get(n).getStrategy().getPoints();
      genScores.add(score);
    }
  }
  
  /**
   * Getter for the scores in the generation being run.
   * @return the ArrayList containing the scores
   */
  public ArrayList<Integer> returnGenerationScores() {
    return genScores;
  }
  
  /**
   * Divides a node's score by the number of neighbours it has in order to maintain
   * fairness between nodes.
   */
  public void normaliseScores() {
    for (Node n : nodes) {
      int neighboursSize = n.getNeighbours().size();
      n.getStrategy().setPoints((n.getStrategy().getPoints()) / neighboursSize);
    }
  }
  
  /**
   * Adds the nodes (and therefore scores) for a generation to the master list 
   * used to display results.

   * @param nodesToAdd the nodes for the current generation
   */
  public void addNodesToMasterList(ArrayList<Node> nodesToAdd) {
    ArrayList<Node> nodeCopies = new ArrayList<Node>();
    for (Node n : nodesToAdd) {
      Strategy nodeStrat = n.getStrategy();
      Strategy copyNodeStrat = null;
      try {
        copyNodeStrat = nodeStrat.getClass().newInstance();
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
      copyNodeStrat.setPoints(nodeStrat.getPoints());
      Node copy = new Node(copyNodeStrat);
      copy.setNodeId(n.getNodeId());
      nodeCopies.add(copy);
    }
    allGenerations.add(nodeCopies);
  }
  
  /**
   * Getter for the master list of all results.

   * @return the ArrayList allGenerations, which contains the reults of each generation
   */
  public ArrayList<ArrayList<Node>> returnAllGenerationResults() {
    return allGenerations;
  }
  
  

}
