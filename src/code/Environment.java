package code;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Environment extends Application {

  // Constants
  private static final int WIDTH = 600;
  private static final int HEIGHT = 600;

  // Scene Objects
  static Scene scene;

  static Rotate rx = new Rotate(0, Rotate.X_AXIS);
  static Rotate ry = new Rotate(0, Rotate.Y_AXIS);
  static Rotate rz = new Rotate(0, Rotate.Z_AXIS);

  static Box box;

  static PerspectiveCamera camera;

  // Mouse Tracking
  private static double mouseX, mouseY = 0;

  public static Parent createContent() {
    // Box
    box = new Box(100, 100, 100);
    box.setMaterial(new PhongMaterial(Color.CORNFLOWERBLUE));
    box.setTranslateX(WIDTH / 2.0);
    box.setTranslateY(HEIGHT / 2.0);
    // When the Rotates are modified, the box will be affected by the modification
    box.getTransforms().addAll(rx, ry, rz);

    // Camera
    camera = new PerspectiveCamera(false);

    return new Group(camera, box);
  }

  public void start(Stage stage) {
    // Stage Settings
    stage.setTitle("RUBIKUBI");
    stage.setResizable(false);

    // Scene
    scene = new Scene(createContent(), WIDTH, HEIGHT);
    stage.setScene(scene);

    // Event Handling
    handleEvents();

    stage.show();
  }

  private static void handleEvents() {
    scene.setOnMousePressed(mouse -> {
      mouseX = mouse.getSceneX();
      mouseY = mouse.getSceneY();
    });

    scene.setOnMouseDragged(mouse -> {
      // Distance offsets from previous to new position
      double dx = (mouseX - mouse.getSceneX()) * 3;
      double dy = (mouseY - mouse.getSceneY()) * 3;

      // Right click will ALWAYS turn the camera no matter what.
      // Left click will only turn the camera if the box isn't interacted with.
      if (mouse.isSecondaryButtonDown() || (mouse.isPrimaryButtonDown() && !box.isPressed())) {
        rx.setAngle(rx.getAngle() - (dy / box.getHeight() * 360) * (Math.PI / 180));
        ry.setAngle(ry.getAngle() - (dx / box.getWidth() * -360) * (Math.PI / 180));
      }

      mouseX = mouse.getSceneX();
      mouseY = mouse.getSceneY();
    });
  }

  public static void main(String args[]) {
    launch(args);
  }

}
