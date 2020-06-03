package javafx;

import javafx.scene.Group;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;
import logic.Face;
import logic.RubikCube;

public class DebugFXRubikSide extends Group {
  
  private static final int BOX_SIZE = 72;
  private static final int BOX_DEPTH = 10;

  public DebugFXRubikSide(Face[] faces) {
    Box boxUL = new Box(BOX_SIZE, BOX_SIZE, BOX_DEPTH); // up left
    Box boxUR = new Box(BOX_SIZE, BOX_SIZE, BOX_DEPTH); // up right
    Box boxDL = new Box(BOX_SIZE, BOX_SIZE, BOX_DEPTH); // down left
    Box boxDR = new Box(BOX_SIZE, BOX_SIZE, BOX_DEPTH); // down right

    this.getChildren().addAll(boxUL, boxUR, boxDL, boxDR);

    Translate t1 = new Translate(40, 40, 0);
    boxUL.getTransforms().addAll(t1);
    
    Translate t2 = new Translate(120, 40, 0);
    boxUR.getTransforms().addAll(t2);
    
    Translate t3 = new Translate(40, 120, 0);
    boxDL.getTransforms().addAll(t3);
    
    Translate t4 = new Translate(120, 120, 0);
    boxDR.getTransforms().addAll(t4);
    
    update(faces);
  }
  
  public void update(Face[] faces) {
    for (int i = 0; i < RubikCube.NB_CELLS; i++) {
      ((Box) this.getChildren().get(i)).setMaterial(faces[i].getMaterial());
    }
  }
}
