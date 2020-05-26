package logic;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public enum Face {
  RED(new Color(1, 0, 0, 1)), BLUE(new Color(0, 0, 1, 1)), ORANGE(
      new Color(1, 0.65, 0, 1)), GREEN(new Color(0, 0.5, 0, 1)), WHITE(
          new Color(1, 1, 1, 1)), YELLOW(new Color(1, 1, 0, 1)), NONE(Color.BLACK);

  private PhongMaterial material;

  Face(Color color) {
    this.material = new PhongMaterial(color);
  }

  public PhongMaterial getMaterial() {
    return this.material;
  }

  public static Face getFace(int index) {
    Face[] faces = values();

    if (index < 0 || index > faces.length) {
      return NONE;
    } else {
      return faces[index];
    }
  }
}
