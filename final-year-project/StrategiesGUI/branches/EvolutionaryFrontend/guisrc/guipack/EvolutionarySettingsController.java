package guipack;

import javafx.fxml.FXML;

public class EvolutionarySettingsController {
  
  private Main mainn;
  
  public EvolutionarySettingsController() {
    
  }
  
  @FXML 
  public void initialize(Main main) {
    setMain(main);
  }
  
  public void setMain(Main mainclass) {
    this.mainn = mainclass;
  }

}
