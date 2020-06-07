package javafx;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import logic.Face;

public class FXRubikPart extends Group {
  
  private static final int SIZE = 72;
  private static final int PIVOT = 36;
  
  private Rectangle[] faces = new Rectangle[6];

  public FXRubikPart() {
    for (int i = 0; i < faces.length; i++) {
      faces[i] = new Rectangle(SIZE, SIZE, Face.NONE.getColor());
      this.getChildren().add(faces[i]);
    }
    
    faces[1].getTransforms().add(new Rotate(-90, PIVOT, PIVOT, PIVOT, Rotate.Y_AXIS));
    faces[2].getTransforms().add(new Rotate(180, PIVOT, PIVOT, PIVOT, Rotate.Y_AXIS));
    faces[3].getTransforms().add(new Rotate(90, PIVOT, PIVOT, PIVOT, Rotate.Y_AXIS));
    faces[4].getTransforms().add(new Rotate(-90, PIVOT, PIVOT, PIVOT, Rotate.X_AXIS));
    faces[5].getTransforms().add(new Rotate(90, PIVOT, PIVOT, PIVOT, Rotate.X_AXIS));
  }
  
  public void update(Face f0, Face f1, Face f2, Face f3, Face f4, Face f5) {
    faces[0].setFill(f0.getColor());
    faces[1].setFill(f1.getColor());
    faces[2].setFill(f2.getColor());
    faces[3].setFill(f3.getColor());
    faces[4].setFill(f4.getColor());
    faces[5].setFill(f5.getColor());
  }
  
}
