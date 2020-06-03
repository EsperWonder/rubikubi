package logic;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

/** Face of a cuboid in a Rubik's Cube. */
public enum Face {
  RED(new Color(1, 0, 0, 1)), BLUE(new Color(0, 0, 1, 1)), ORANGE(new Color(1, 0.65, 0, 1)), GREEN(
      new Color(0, 0.5, 0, 1)), WHITE(
          new Color(1, 1, 1, 1)), YELLOW(new Color(1, 1, 0, 1)), NONE(new Color(0.2, 0.2, 0.2, 1));

  /** Material used by the face. */
  private PhongMaterial material;

  Face(Color color) {
    this.material = new PhongMaterial(color);
  }

  /** Returns the color, commonly used in 2D graphics. */
  public Color getColor() {
    return this.material.getDiffuseColor();
  }
  
  /** Returns a material, commonly used in 3D graphics. */
  public PhongMaterial getMaterial() {
    return this.material;
  }

  /**
   * Returns the face corresponding to the given index.<br>
   * If not found, returns {@code Face.NONE}.
   */
  public static Face getFace(int index) {
    Face[] faces = values();

    if (index < 0 || index > faces.length) {
      return NONE;
    } else {
      return faces[index];
    }
  }
}
