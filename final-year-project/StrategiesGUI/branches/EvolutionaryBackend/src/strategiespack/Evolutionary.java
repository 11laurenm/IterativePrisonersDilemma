package strategiespack;

import java.util.ArrayList;

public class Evolutionary {
  
  ArrayList<Strategy> strategies;
  Strategy[][] gameGrid;
  
  public Evolutionary(int gridX, int gridY, ArrayList<Strategy> strats){
    gameGrid = new Strategy[gridX][gridY];
    strategies = strats;
  }
  
  
  
  

}
