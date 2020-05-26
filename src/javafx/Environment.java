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
  static Button btn_fi = new Button("Fi");
  static Button btn_b = new Button("B");
  static Button btn_bi = new Button("Bi");
  static Button btn_l = new Button("L");
  static Button btn_li = new Button("Li");
  static Button btn_r = new Button("R");
  static Button btn_ri = new Button("Ri");
  static Button btn_u = new Button("U");
  static Button btn_ui = new Button("Ui");
  static Button btn_d = new Button("D");
  static Button btn_di = new Button("Di");

  // 3D Scene Objects
  static SubScene subscene;

  static FXRubikCube fx_cube = new FXRubikCube(rubikCube);

  static Rotate rx = new Rotate(0, 80, 80, 80, Rotate.X_AXIS);
  static Rotate ry = new Rotate(0, 80, 80, 80, Rotate.Y_AXIS);

  static PerspectiveCamera camera = new PerspectiveCamera(false);

  ///////////////
  // Functions //
  ///////////////

  private static Parent createContent() {
    BorderPane content = new BorderPane();
    content.setPadding(new Insets(8));
    content.setBackground(BG_2D);

    // 3D Content
    content.setCenter(create3DContent());

    // Rotation UI
    HBox fBox = new HBox(8, btn_f, btn_fi);
    HBox bBox = new HBox(8, btn_b, btn_bi);
    HBox lBox = new HBox(8, btn_l, btn_li);
    HBox rBox = new HBox(8, btn_r, btn_ri);
    HBox uBox = new HBox(8, btn_u, btn_ui);
    HBox dBox = new HBox(8, btn_d, btn_di);

    content.setRight(new VBox(8, fBox, bBox, lBox, rBox, uBox, dBox));

    return content;
  }

  private static SubScene create3DContent() {
    HBox content = new HBox();
    content.setAlignment(Pos.CENTER);

    subscene = new SubScene(content, 500, 500, true, SceneAntialiasing.BALANCED);

    fx_cube.setTranslateZ(-200);
    fx_cube.getTransforms().addAll(rx, ry);
    fx_cube.setPickOnBounds(true);

    subscene.setCamera(camera);

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
    btn_fi.setOnAction(event -> {
      rubikCube.Fi();
      fx_cube.update();
    });

    btn_b.setOnAction(event -> {
      rubikCube.B();
      fx_cube.update();
    });
    btn_bi.setOnAction(event -> {
      rubikCube.Bi();
      fx_cube.update();
    });
    
    btn_l.setOnAction(event -> {
      rubikCube.L();
      fx_cube.update();
    });
    btn_li.setOnAction(event -> {
      rubikCube.Li();
      fx_cube.update();
    });
    
    btn_r.setOnAction(event -> {
      rubikCube.R();
      fx_cube.update();
    });
    btn_ri.setOnAction(event -> {
      rubikCube.Ri();
      fx_cube.update();
    });
    
    btn_u.setOnAction(event -> {
      rubikCube.U();
      fx_cube.update();
    });
    btn_ui.setOnAction(event -> {
      rubikCube.Ui();
      fx_cube.update();
    });
    
    btn_d.setOnAction(event -> {
      rubikCube.D();
      fx_cube.update();
    });
    btn_di.setOnAction(event -> {
      rubikCube.Di();
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
      camera.setTranslateZ(camera.getTranslateZ() + scroll.getDeltaY());
    });
  }

}
