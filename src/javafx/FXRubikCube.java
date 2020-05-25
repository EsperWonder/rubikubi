package javafx;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import logic.RubikCube;

public class FXRubikCube extends Group {

  static final Insets insets = new Insets(8);

  private RubikCube source;

  public FXRubikCube(RubikCube src) {
    this.source = src;

    FXRubikSide side_0 = new FXRubikSide(source.getSide(0));
    FXRubikSide side_1 = new FXRubikSide(source.getSide(1));
    FXRubikSide side_2 = new FXRubikSide(source.getSide(2));
    FXRubikSide side_3 = new FXRubikSide(source.getSide(3));
    FXRubikSide side_4 = new FXRubikSide(source.getSide(4));
    FXRubikSide side_5 = new FXRubikSide(source.getSide(5));

    this.getChildren().addAll(side_0, side_1, side_2, side_3, side_4, side_5);

    // TODO
    // no need to "translate then rotate"
    // instead, rotate the sides around the cube's center
    // in other words, the rotations' pivot is the center coordinates of the cube

    Rotate r1_1 = new Rotate(-90, 80, 80, 80, Rotate.Y_AXIS); // place around cube
    Rotate r1_2 = new Rotate(-90, 80, 80, 80, Rotate.Z_AXIS); // rotate in place
    side_1.getTransforms().addAll(r1_1, r1_2);

    Rotate r2_1 = new Rotate(180, 80, 80, 80, Rotate.Y_AXIS);
    //TODO add potential rotation on self to rectify direction
    side_2.getTransforms().addAll(r2_1);

    Rotate r3_1 = new Rotate(90, 80, 80, 80, Rotate.Y_AXIS);
    Rotate r3_2 = new Rotate(-90, 80, 80, 80, Rotate.Z_AXIS);
    side_3.getTransforms().addAll(r3_1, r3_2);

    Rotate r4_1 = new Rotate(-90, 80, 80, 80, Rotate.X_AXIS);
    Rotate r4_2 = new Rotate(90, 80, 80, 80, Rotate.Z_AXIS);
    side_4.getTransforms().addAll(r4_1, r4_2);
    
    Rotate r5_1 = new Rotate(90, 80, 80, 80, Rotate.X_AXIS);
    Rotate r5_2 = new Rotate(90, 80, 80, 80, Rotate.Z_AXIS);
    side_5.getTransforms().addAll(r5_1, r5_2);

  }

  public void update() {
    for (int i = 0; i < RubikCube.NB_SIDES; i++) {
      ((FXRubikSide) this.getChildren().get(i)).update(source.getSide(i));
    }
  }

}
