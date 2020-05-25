package javafx;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Box;
import logic.Face;
import logic.RubikCube;

public class FXRubikSide extends GridPane {

  public FXRubikSide(Face[] faces) {
    this.add(createBox(), 0, 0);
    this.add(createBox(), 0, 1);
    this.add(createBox(), 1, 0);
    this.add(createBox(), 1, 1);
    
    update(faces);
  }
  
  private Box createBox() {
    Box box = new Box(64, 64, 10);
    GridPane.setMargin(box, Environment.insets);
    
    return box;
  }
  
  public void update(Face[] faces) {
    for (int i = 0; i < RubikCube.NB_CELLS; i++) {
      ((Box) this.getChildren().get(i)).setMaterial(faces[i].getMaterial());
    }
  }
}
