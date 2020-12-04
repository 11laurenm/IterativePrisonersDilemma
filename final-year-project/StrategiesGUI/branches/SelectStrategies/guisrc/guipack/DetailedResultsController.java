package guipack;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import strategiespack.Strategy;

public class DetailedResultsController {
	
	private Main mainn;
	
	@FXML
	private Label resultsLabel;
	
	public void setMain(Main Mainclass) {
        this.mainn = Mainclass;
    }
	
	@FXML void initialize() {
		
	}
	
	void setResults(ArrayList<ArrayList<Character> > decisions, ArrayList<ArrayList<Integer> > points, ArrayList<Strategy> strats, ArrayList<Integer> lengths) {
		String labelText = "";
		ArrayList<String> pairings = new ArrayList<>();
		int pairNumber = 0;
		
		labelText = labelText + "Length of game 1: " + Integer.toString(lengths.get(0));
		labelText = labelText + " Length of game 2: " + Integer.toString(lengths.get(1));
		labelText = labelText + " Length of game 3: " + Integer.toString(lengths.get(2));
		labelText = labelText + "\n";
		
		for(int i = 0; i < strats.size(); i++) {
			for(int j = i; j < strats.size(); j++) {
				pairings.add(strats.get(i).nameProperty().getValue());
				pairings.add(strats.get(j).nameProperty().getValue());
			}
		}
		for(int set = 0; set<decisions.size(); set = set + 3) {
			labelText = labelText + pairings.get(pairNumber) + " playing against ";
			labelText = labelText + pairings.get(pairNumber + 1);
			labelText = labelText + "\n";
			pairNumber = pairNumber + 2;
			int roundNumber = 0;
			for(int j = 0; j < 3; j++) {
				ArrayList<Character> decisionsSet = decisions.get(set + j);
				ArrayList<Integer> pointsSet = points.get(set + j);
				for(int i = 0; i < decisionsSet.size(); i = i+2) {
					roundNumber++;
					labelText = labelText + "Round ";
					labelText = labelText + roundNumber + ": ";
					labelText = labelText + decisionsSet.get(i) + " vs ";
					labelText = labelText + decisionsSet.get(i + 1) + " ";
					labelText = labelText + pointsSet.get(i) + " points vs ";
					labelText = labelText + pointsSet.get(i + 1) + " points";
					labelText = labelText + "\n";
				}
			}
		}
		
		resultsLabel.setText(labelText);
		
	}
	
	

}
