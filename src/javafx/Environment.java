package javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import logic.RubikCube;

public class Environment extends Application {

  ///////////////
  // CONSTANTS //
  ///////////////

  /** Window width. */
  private static final int WIDTH = 800;
  /** Window height. */
  private static final int HEIGHT = 600;

  static final Insets insets = new Insets(8);

  private static final Background BG_2D =
      new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null));

  /////////////
  // OBJECTS //
  /////////////

  // Logic Objects
  static RubikCube rubikCube = new RubikCube();

  // 2D Scene Objects
  static Scene scene;
  static Button btn_f = new Button("F");

  // 3D Scene Objects
  static SubScene subscene;

  static FXRubikCube fx_cube = new FXRubikCube(rubikCube);

  static Rotate rx = new Rotate(0, 80, 80, 80, Rotate.X_AXIS);
  static Rotate ry = new Rotate(0, 80, 80, 80, Rotate.Y_AXIS);

  static PerspectiveCamera camera = new PerspectiveCamera(true);

  ///////////////
  // Functions //
  ///////////////

  private static Parent createContent() {
    BorderPane content = new BorderPane();
    content.setPadding(new Insets(8));
    content.setBackground(BG_2D);

    content.setCenter(create3DContent());
    content.setRight(new VBox(8, btn_f));
    return content;
  }

  private static SubScene create3DContent() {
    HBox content = new HBox();
    content.setAlignment(Pos.CENTER);

    subscene = new SubScene(content, 500, 500, true, SceneAntialiasing.BALANCED);

    fx_cube.setTranslateZ(-200);
    fx_cube.getTransforms().addAll(rx, ry);
    fx_cube.setPickOnBounds(true);
    
    //TODO setCamera() to subscene, you didn't add it to it yet!

    content.getChildren().addAll(fx_cube);
    return subscene;
  }

  public void start(Stage stage) {
    // Stage Settings
    stage.setTitle("RUBIKUBI");
    stage.setResizable(false);

    // Scene
    scene = new Scene(createContent(), WIDTH, HEIGHT, true);
    stage.setScene(scene);

    // Event Handling
    handleButtons();
    handleMouse();

    // Run
    stage.centerOnScreen();
    stage.show();
  }

  public static void main(String args[]) {
    launch(args);
  }

  private static double mouseX, mouseY = 0;

  private static void handleButtons() {
    btn_f.setOnAction(event -> {
      rubikCube.F();
      fx_cube.update();
    });
  }

  private static void handleMouse() {
    subscene.setOnMousePressed(mouse -> {
      // Get mouse position
      mouseX = mouse.getSceneX();
      mouseY = mouse.getSceneY();
    });

    subscene.setOnMouseDragged(mouse -> {
      // Distance offsets from old to current position
      double dx = (mouseX - mouse.getSceneX());
      double dy = (mouseY - mouse.getSceneY());

      // Left click turns the camera if not on rubik, while right click always turns the camera
      if (mouse.isSecondaryButtonDown()) {
        rx.setAngle(rx.getAngle() - dy);
        ry.setAngle(ry.getAngle() + dx);

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
    
    scene.setOnScroll(scroll -> {
      camera.setTranslateX(camera.getTranslateX() + 1);
    });
  }

}
