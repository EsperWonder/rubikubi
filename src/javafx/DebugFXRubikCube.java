package javafx;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import logic.RubikCube;

public class DebugFXRubikCube extends Group {

  static final Insets insets = new Insets(8);

  private RubikCube source;

  public DebugFXRubikCube(RubikCube src) {
    this.source = src;

    DebugFXRubikSide side_0 = new DebugFXRubikSide(source.getSide(0));
    DebugFXRubikSide side_1 = new DebugFXRubikSide(source.getSide(1));
    DebugFXRubikSide side_2 = new DebugFXRubikSide(source.getSide(2));
    DebugFXRubikSide side_3 = new DebugFXRubikSide(source.getSide(3));
    DebugFXRubikSide side_4 = new DebugFXRubikSide(source.getSide(4));
    DebugFXRubikSide side_5 = new DebugFXRubikSide(source.getSide(5));

    this.getChildren().addAll(side_0, side_1, side_2, side_3, side_4, side_5);

    Rotate r1 = new Rotate(-90, 80, 80, 80, Rotate.Y_AXIS);
    side_1.getTransforms().addAll(r1);

    Rotate r2 = new Rotate(180, 80, 80, 80, Rotate.Y_AXIS);
    side_2.getTransforms().addAll(r2);

    Rotate r3 = new Rotate(90, 80, 80, 80, Rotate.Y_AXIS);
    side_3.getTransforms().addAll(r3);

    Rotate r4 = new Rotate(-90, 80, 80, 80, Rotate.X_AXIS);
    side_4.getTransforms().addAll(r4);
    
    Rotate r5 = new Rotate(90, 80, 80, 80, Rotate.X_AXIS);
    side_5.getTransforms().addAll(r5);

  }

  public void update() {
    for (int i = 0; i < RubikCube.NB_SIDES; i++) {
      ((DebugFXRubikSide) this.getChildren().get(i)).update(source.getSide(i));
    }
  }

}
