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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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

  // Private Variables
  private static double mouseX, mouseY = 0;
  
  // Rubik Logic
  static RubikCube rubikCube = new RubikCube();

  // 2D Scene Objects
  static Scene scene;
  static GridPane buttons = new GridPane();
  
  static Button btn_shuffle = new Button("Shuffle");
  
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

  static DebugFXRubikCube fx_cube = new DebugFXRubikCube(rubikCube);
  static FXRubikCube fx_cube2 = new FXRubikCube(rubikCube);

  static Rotate rx = new Rotate(0, 80, 80, 80, Rotate.X_AXIS);
  static Rotate ry = new Rotate(0, 80, 80, 80, Rotate.Y_AXIS);

  static PerspectiveCamera camera = new PerspectiveCamera(false);

  ///////////////
  // Functions //
  ///////////////

  private static Parent createContent() {
    // 2D Container
    BorderPane content = new BorderPane();
    content.setPadding(new Insets(8));
    content.setBackground(BG_2D);

    // 3D Content
    content.setCenter(create3DContent());

    // Rotation UI
    buttons.add(btn_f, 0, 0);
    buttons.add(btn_fi, 1, 0);
    buttons.add(btn_b, 0, 1);
    buttons.add(btn_bi, 1, 1);
    
    buttons.add(btn_l, 0, 2);
    buttons.add(btn_li, 1, 2);
    buttons.add(btn_r, 0, 3);
    buttons.add(btn_ri, 1, 3);
    
    buttons.add(btn_u, 0, 4);
    buttons.add(btn_ui, 1, 4);
    buttons.add(btn_d, 0, 5);
    buttons.add(btn_di, 1, 5);
    
    content.setRight(buttons);
    content.setLeft(btn_shuffle);

    return content;
  }

  private static SubScene create3DContent() {
    // 3D Container
    StackPane content = new StackPane();
    content.setAlignment(Pos.CENTER);

    // SubScene
    subscene = new SubScene(content, 500, 500, true, SceneAntialiasing.BALANCED);
    
    // Rubik's Cube
    fx_cube.setTranslateX(-150);
    fx_cube.setTranslateY(-150);
    fx_cube.setTranslateZ(-200);
    fx_cube.setScaleX(0.2);
    fx_cube.setScaleY(0.2);
    fx_cube.setScaleZ(0.2);
    fx_cube.getTransforms().addAll(rx, ry);
    fx_cube.setPickOnBounds(true);
    
    fx_cube2.setTranslateZ(-200);
    fx_cube2.getTransforms().addAll(rx, ry);

    // Camera
    subscene.setCamera(camera);

    content.getChildren().addAll(fx_cube, fx_cube2);
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
    handleRotationButtons();
    handleMouse();

    // Start
    stage.centerOnScreen();
    stage.show();
  }

  public static void main(String args[]) {
    launch(args);
  }

  private static void handleRotationButtons() {
    btn_shuffle.setOnAction(event -> {
      rubikCube.shuffle();
      fx_cube2.update();
      fx_cube.update();
    });
    
    btn_f.setOnAction(event -> {
      rubikCube.F();
      fx_cube2.F();
      fx_cube.update();
    });
    btn_fi.setOnAction(event -> {
      rubikCube.Fi();
      fx_cube2.Fi();
      fx_cube.update();
    });

    btn_b.setOnAction(event -> {
      rubikCube.B();
      fx_cube2.B();
      fx_cube.update();
    });
    btn_bi.setOnAction(event -> {
      rubikCube.Bi();
      fx_cube2.Bi();
      fx_cube.update();
    });
    
    btn_l.setOnAction(event -> {
      rubikCube.L();
      fx_cube2.L();
      fx_cube.update();
    });
    btn_li.setOnAction(event -> {
      rubikCube.Li();
      fx_cube2.Li();
      fx_cube.update();
    });
    
    btn_r.setOnAction(event -> {
      rubikCube.R();
      fx_cube2.R();
      fx_cube.update();
    });
    btn_ri.setOnAction(event -> {
      rubikCube.Ri();
      fx_cube2.Ri();
      fx_cube.update();
    });
    
    btn_u.setOnAction(event -> {
      rubikCube.U();
      fx_cube2.U();
      fx_cube.update();
    });
    btn_ui.setOnAction(event -> {
      rubikCube.Ui();
      fx_cube2.Ui();
      fx_cube.update();
    });
    
    btn_d.setOnAction(event -> {
      rubikCube.D();
      fx_cube2.D();
      fx_cube.update();
    });
    btn_di.setOnAction(event -> {
      rubikCube.Di();
      fx_cube2.Di();
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

      // Right-click drag to move the camera
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
  }

}
