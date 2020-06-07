package javafx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import logic.Face;
import logic.RubikCube;

public class FXRubikCube extends Group {

  private enum Axis {
    X(1), Y(2), Z(3);

    int value;

    Axis(int i) {
      this.value = i;
    }

    private int getValue() {
      return this.value;
    }
  }

  private static final Duration ANIM_DURATION = Duration.millis(100);
  private static final int LO_PIVOT = 4;
  private static final int HI_PIVOT = 84;

  private RubikCube source;

  /** When an animation plays, this variable must be true. */
  private boolean isAnimating = false;

  /** Cuboids that make up the Rubik's Cube. */
  private FXRubikPart[] parts = new FXRubikPart[8];

  public FXRubikCube(RubikCube src) {
    this.source = src;

    for (int i = 0; i < parts.length; i++) {
      parts[i] = new FXRubikPart();
      this.getChildren().add(parts[i]);
    }

    parts[0].getTransforms().add(new Translate(LO_PIVOT, LO_PIVOT, HI_PIVOT));
    parts[1].getTransforms().add(new Translate(HI_PIVOT, LO_PIVOT, HI_PIVOT));
    parts[2].getTransforms().add(new Translate(LO_PIVOT, LO_PIVOT, LO_PIVOT));
    parts[3].getTransforms().add(new Translate(HI_PIVOT, LO_PIVOT, LO_PIVOT));
    parts[4].getTransforms().add(new Translate(LO_PIVOT, HI_PIVOT, HI_PIVOT));
    parts[5].getTransforms().add(new Translate(HI_PIVOT, HI_PIVOT, HI_PIVOT));
    parts[6].getTransforms().add(new Translate(LO_PIVOT, HI_PIVOT, LO_PIVOT));
    parts[7].getTransforms().add(new Translate(HI_PIVOT, HI_PIVOT, LO_PIVOT));

    setTransforms(parts[0], HI_PIVOT, HI_PIVOT, LO_PIVOT);
    setTransforms(parts[1], LO_PIVOT, HI_PIVOT, LO_PIVOT);
    setTransforms(parts[2], HI_PIVOT, HI_PIVOT, HI_PIVOT);
    setTransforms(parts[3], LO_PIVOT, HI_PIVOT, HI_PIVOT);
    setTransforms(parts[4], HI_PIVOT, LO_PIVOT, LO_PIVOT);
    setTransforms(parts[5], LO_PIVOT, LO_PIVOT, LO_PIVOT);
    setTransforms(parts[6], HI_PIVOT, LO_PIVOT, HI_PIVOT);
    setTransforms(parts[7], LO_PIVOT, LO_PIVOT, HI_PIVOT);
    
    update();
  }

  /** Updates the faces' colors to match the source. */
  public void update() {
    parts[0].update(Face.NONE, Face.NONE, source.getFace(2, 1), source.getFace(3, 0), source.getFace(4, 0), Face.NONE);
    parts[1].update(Face.NONE, source.getFace(1, 1), source.getFace(2, 0), Face.NONE, source.getFace(4, 1), Face.NONE);
    parts[2].update(source.getFace(0, 0), Face.NONE, Face.NONE, source.getFace(3, 1), source.getFace(4, 2), Face.NONE);
    parts[3].update(source.getFace(0, 1), source.getFace(1, 0), Face.NONE, Face.NONE, source.getFace(4, 3), Face.NONE);
    parts[4].update(Face.NONE, Face.NONE, source.getFace(2, 3), source.getFace(3, 2), Face.NONE, source.getFace(5, 2));
    parts[5].update(Face.NONE, source.getFace(1, 3), source.getFace(2, 2), Face.NONE, Face.NONE, source.getFace(5, 3));
    parts[6].update(source.getFace(0, 2), Face.NONE, Face.NONE, source.getFace(3, 3), Face.NONE, source.getFace(5, 0));
    parts[7].update(source.getFace(0, 3), source.getFace(1, 2), Face.NONE, Face.NONE, Face.NONE, source.getFace(5, 1));
  }

  /** Resets the rotation of given parts. */
  public void resetRotations(int... partIndexes) {
    for (int partIndex : partIndexes) {
      for (int i = 1; i <= 3; i++) {
        ((Rotate) parts[partIndex].getTransforms().get(i)).setAngle(0);
      }
    }
  }

  private void setTransforms(FXRubikPart part, int x, int y, int z) {
    part.getTransforms().add(1, new Rotate(0, x, y, z, Rotate.X_AXIS));
    part.getTransforms().add(2, new Rotate(0, x, y, z, Rotate.Y_AXIS));
    part.getTransforms().add(3, new Rotate(0, x, y, z, Rotate.Z_AXIS));
  }

  private void rotate(int part1, int part2, int part3, int part4, Axis axis, boolean clockwise) {
    if (!isAnimating) {
      isAnimating = true;

      rotatePart(part1, axis, clockwise);
      rotatePart(part2, axis, clockwise);
      rotatePart(part3, axis, clockwise);
      rotatePart(part4, axis, clockwise).setOnFinished(e -> {
        isAnimating = false;
        update();
        resetRotations(part1, part2, part3, part4);
      });

    }
  }

  private Timeline rotatePart(int partIndex, Axis axis, boolean clockwise) {
    int addedAngle = clockwise ? 90 : -90;

    Rotate rotate = (Rotate) parts[partIndex].getTransforms().get(axis.getValue());

    KeyFrame keyStart =
        new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), rotate.getAngle()));
    KeyFrame keyEnd = new KeyFrame(ANIM_DURATION,
        new KeyValue(rotate.angleProperty(), rotate.getAngle() + addedAngle));

    Timeline tl = new Timeline(keyStart, keyEnd);
    tl.play();

    return tl;
  }

  ///////////////
  // ROTATIONS //
  ///////////////

  public void F() {
    this.rotate(2, 3, 6, 7, Axis.Z, true);
  }

  public void Fi() {
    this.rotate(2, 3, 6, 7, Axis.Z, false);
  }

  public void B() {
    this.rotate(0, 1, 4, 5, Axis.Z, false);
  }

  public void Bi() {
    this.rotate(0, 1, 4, 5, Axis.Z, true);
  }

  public void L() {
    this.rotate(0, 2, 4, 6, Axis.X, true);
  }

  public void Li() {
    this.rotate(0, 2, 4, 6, Axis.X, false);
  }

  public void R() {
    this.rotate(1, 3, 5, 7, Axis.X, false);
  }

  public void Ri() {
    this.rotate(1, 3, 5, 7, Axis.X, true);
  }

  public void U() {
    this.rotate(0, 1, 2, 3, Axis.Y, true);
  }

  public void Ui() {
    this.rotate(0, 1, 2, 3, Axis.Y, false);
  }

  public void D() {
    this.rotate(4, 5, 6, 7, Axis.Y, false);
  }

  public void Di() {
    this.rotate(4, 5, 6, 7, Axis.Y, true);
  }

}
