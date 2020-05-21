package rubik;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public enum Face {
  RED(Color.RED), BLUE(Color.BLUE), ORANGE(Color.ORANGE), GREEN(Color.GREEN), WHITE(
      Color.WHITE), YELLOW(Color.YELLOW), NONE(Color.BLACK);

  private PhongMaterial material;

  Face(Color color) {
    this.material = new PhongMaterial(color);
  }

  public PhongMaterial getMaterial() {
    return this.material;
  }
}
