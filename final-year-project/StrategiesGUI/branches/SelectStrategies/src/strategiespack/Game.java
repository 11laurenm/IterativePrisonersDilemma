package strategiespack;

import java.util.ArrayList;

/** A class that runs one or more games of the PD
 * 
 * @author Lauren Moore
 *
 */
public class Game {
    
	/**
	 * Each strategy has: a history - an arraylist of every decision made in this game
	 *					  a move - the move selected for this round
	 * RoundScores is each strategy's score for the current round
	 * length is the amount of rounds to be played
	 */ 				  
    public ArrayList<Character> HistoryStrategy1;
    public ArrayList<Character> HistoryStrategy2;
    public ArrayList<Integer> RoundScores;
    Strategy strategy1;
    Strategy strategy2;
    char strategy1move;
    char strategy2move;
    int length;
    boolean dummyStrat;
    ArrayList<Integer> payoffs;
    
    /**
     * 
     * @param strat1 a strategy being played
     * @param strat2 the other strategy being played
     * @param rounds the amount of rounds to be played
     */
    public Game(Strategy strat1, Strategy strat2, int rounds, ArrayList<Integer> payoffList) {
        HistoryStrategy1 = new ArrayList<>();
        HistoryStrategy2 = new ArrayList<>();
        RoundScores = new ArrayList<>();
        strategy1 = strat1;
        strategy2 = strat2;
        length = rounds;
        dummyStrat = false;
        payoffs = payoffList;
    }
    
    /**
     * When given a history, returns the most recent decision made.
     * @param History - an arraylist of decisions made
     * @return the most recent decision in the history
     */
    public char getLastMove(ArrayList<Character> History) {
        if(History.size() == 0) {
            return 'n';
        }
        return History.get(History.size()-1);
    }
    
    /**
     * Adds a decision to the history
     * @param move the decision that was made
     * @param History the history arraylist that the decision should be added to
     */
    public void setLastMove(char move, ArrayList<Character> History) {
        History.add(move);
    }
    
    /**
     * Method that handles the running of the game.
     * Uses the round number provided on creation to run that many rounds.
     */
    public void playGame() {
    	
    	for(int round = 0; round<length; round++) {
	        strategy1move = strategy1.getDecision(getLastMove(HistoryStrategy2), HistoryStrategy2, getLastMove(HistoryStrategy1));
	        strategy2move = strategy2.getDecision(getLastMove(HistoryStrategy1), HistoryStrategy1, getLastMove(HistoryStrategy2));
	        
	        setLastMove(strategy1move, HistoryStrategy1);
	        setLastMove(strategy2move, HistoryStrategy2);
	        
	        RoundScores = calculateScores(strategy1move, strategy2move);
	        
	        strategy1.addPoints(RoundScores.get(0));
	        if(!isDummy()) {
	        	strategy2.addPoints(RoundScores.get(1));
	        }
	        
	        
	        RoundScores.clear();
    	}
        
    }
    
    /**
     * Used to calculate the scores of each strategy given their decisions.
     * @param decision1 - decision made by strategy 1
     * @param decision2 - decision made by strategy 2
     * @return an arraylist containing the scores for both strategies
     */
    public ArrayList<Integer> calculateScores(char decision1, char decision2) {
        ArrayList<Integer> scores = new ArrayList();
        if(decision1 == 'c') {
            if(decision2 == 'c') {
                scores.add(payoffs.get(0));
                scores.add(payoffs.get(0));
            } else {
            	scores.add(payoffs.get(2));
                scores.add(payoffs.get(1));
            }
        } else {
            if(decision2 == 'c') {
            	scores.add(payoffs.get(1));
            	scores.add(payoffs.get(2));  
            } else {
            	scores.add(payoffs.get(3));
            	scores.add(payoffs.get(3));
            } 
        }
        
        return scores;
    }
    
    public boolean isDummy() {
    	if(strategy1.equals(strategy2)) {
    		return true;
    	}
    	return false;
    }
   
}