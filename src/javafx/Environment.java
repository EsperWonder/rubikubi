package javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import rubik.Face;

public class Environment extends Application {

  /** Window width. */
  private static final int WIDTH = 600;
  /** Window height. */
  private static final int HEIGHT = 600;

  // Scene Objects
  static Scene scene;
  static Box rubik = new Box(200, 200, 200);

  static Rotate rx = new Rotate(0, Rotate.X_AXIS);
  static Rotate ry = new Rotate(0, Rotate.Y_AXIS);
  
  static PerspectiveCamera camera = new PerspectiveCamera(false);

  // Mouse Tracking
  private static double mouseX, mouseY = 0;

  public static Parent createContent() {
    rubik.setMaterial(Face.RED.getMaterial());
    translate(rubik, WIDTH / 2, HEIGHT / 2);
    rubik.getTransforms().addAll(rx, ry);

    return new Group(camera, rubik);
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

    stage.centerOnScreen();
    stage.show();
  }

  public static void main(String args[]) {
    launch(args);
  }

  private static void handleEvents() {
    scene.setOnMousePressed(mouse -> {
      // Get mouse position
      mouseX = mouse.getSceneX();
      mouseY = mouse.getSceneY();
    });

    scene.setOnMouseDragged(mouse -> {
      // Distance offsets from old to current position
      double dx = (mouseX - mouse.getSceneX()) * 10;
      double dy = (mouseY - mouse.getSceneY()) * 10;

      // Left click turns the camera if not on rubik, while right click always turns the camera
      if (mouse.isSecondaryButtonDown() || (mouse.isPrimaryButtonDown() && !rubik.isPressed())) {
        rx.setAngle(rx.getAngle() - (dy / rubik.getHeight() * 360) * (Math.PI / 180));
        ry.setAngle(ry.getAngle() - (dx / rubik.getWidth() * -360) * (Math.PI / 180));

        // Limits on rx
        if (rx.getAngle() > 90) {
          rx.setAngle(90);
        } else if (rx.getAngle() < -90) {
          rx.setAngle(-90);
        }
      }

      // Get mouse position
      mouseX = mouse.getSceneX();
      mouseY = mouse.getSceneY();
    });
  }

  private static void translate(Node n, double x, double y) {
    n.setTranslateX(x);
    n.setTranslateY(y);
  }
}
