package logic;

import java.util.concurrent.ThreadLocalRandom;

/** Data structure of a 2x2x2 Rubik's Cube. */
public class RubikCube {

  /** Number of sides the cube has. */
  public static final int NB_SIDES = 6;
  /** Number of cells each side has. */
  public static final int NB_CELLS = 4;

  /** Default number of rotations when shuffling. */
  public static final int NB_SHUFFLES = 25;

  /** Faces of the Rubik's Cube. */
  private Face[][] cells;

  public RubikCube() {
    this.cells = new Face[NB_SIDES][NB_CELLS];
    this.init();
  }

  /** Initializes the cube's faces. */
  public void init() {
    for (int s = 0; s < NB_SIDES; s++) {
      for (int c = 0; c < NB_CELLS; c++) {
        this.cells[s][c] = Face.getFace(s);
      }
    }
  }

  /** Returns an array of faces. */
  public Face[] getSide(int side) {
    return this.cells[side];
  }

  /** Returns a single face. */
  public Face getFace(int side, int face) {
    return this.getSide(side)[face];
  }

  /** Applies random rotations to the cube. */
  public void shuffle() {
    this.shuffle(NB_SHUFFLES);
  }

  /** Applies a given number of rotations to the cube. */
  public void shuffle(int shuffles) {
    for (int i = 0; i < shuffles; i++) {

      switch (ThreadLocalRandom.current().nextInt(0, 11 + 1)) {
        case 0:
          F();
          break;

        case 1:
          Fi();
          break;

        case 2:
          B();
          break;

        case 3:
          Bi();
          break;

        case 4:
          L();
          break;

        case 5:
          Li();
          break;

        case 6:
          R();
          break;

        case 7:
          Ri();
          break;

        case 8:
          U();
          break;

        case 9:
          Ui();
          break;

        case 10:
          D();
          break;

        case 11:
          Di();
          break;
      }

    }
  }

  ///////////////
  // ROTATIONS //
  ///////////////

  public void F() {
    Face tmp;

    // Ring rotation
    tmp = cells[3][1];
    cells[3][1] = cells[5][0];
    cells[5][0] = cells[1][2];
    cells[1][2] = cells[4][3];
    cells[4][3] = tmp;

    tmp = cells[3][3];
    cells[3][3] = cells[5][1];
    cells[5][1] = cells[1][0];
    cells[1][0] = cells[4][2];
    cells[4][2] = tmp;

    // Face rotation
    tmp = cells[0][0];
    cells[0][0] = cells[0][2];
    cells[0][2] = cells[0][3];
    cells[0][3] = cells[0][1];
    cells[0][1] = tmp;
  }

  public void Fi() {
    Face tmp;

    // Ring rotation
    tmp = cells[4][3];
    cells[4][3] = cells[1][2];
    cells[1][2] = cells[5][0];
    cells[5][0] = cells[3][1];
    cells[3][1] = tmp;

    tmp = cells[4][2];
    cells[4][2] = cells[1][0];
    cells[1][0] = cells[5][1];
    cells[5][1] = cells[3][3];
    cells[3][3] = tmp;

    // Face rotation
    tmp = cells[0][1];
    cells[0][1] = cells[0][3];
    cells[0][3] = cells[0][2];
    cells[0][2] = cells[0][0];
    cells[0][0] = tmp;
  }

  public void B() {
    Face tmp;

    // Ring rotation
    tmp = cells[5][3];
    cells[5][3] = cells[3][2];
    cells[3][2] = cells[4][0];
    cells[4][0] = cells[1][1];
    cells[1][1] = tmp;

    tmp = cells[5][2];
    cells[5][2] = cells[3][0];
    cells[3][0] = cells[4][1];
    cells[4][1] = cells[1][3];
    cells[1][3] = tmp;

    // Face rotation
    tmp = cells[2][2];
    cells[2][2] = cells[2][3];
    cells[2][3] = cells[2][1];
    cells[2][1] = cells[2][0];
    cells[2][0] = tmp;
  }

  public void Bi() {
    Face tmp;

    // Ring rotation
    tmp = cells[1][1];
    cells[1][1] = cells[4][0];
    cells[4][0] = cells[3][2];
    cells[3][2] = cells[5][3];
    cells[5][3] = tmp;

    tmp = cells[1][3];
    cells[1][3] = cells[4][1];
    cells[4][1] = cells[3][0];
    cells[3][0] = cells[5][2];
    cells[5][2] = tmp;

    // Face rotation
    tmp = cells[2][0];
    cells[2][0] = cells[2][1];
    cells[2][1] = cells[2][3];
    cells[2][3] = cells[2][2];
    cells[2][2] = tmp;
  }

  public void L() {
    Face tmp;

    // Ring rotation
    tmp = cells[2][1];
    cells[2][1] = cells[5][2];
    cells[5][2] = cells[0][2];
    cells[0][2] = cells[4][2];
    cells[4][2] = tmp;

    tmp = cells[2][3];
    cells[2][3] = cells[5][0];
    cells[5][0] = cells[0][0];
    cells[0][0] = cells[4][0];
    cells[4][0] = tmp;

    // Face rotation
    tmp = cells[3][2];
    cells[3][2] = cells[3][3];
    cells[3][3] = cells[3][1];
    cells[3][1] = cells[3][0];
    cells[3][0] = tmp;
  }

  public void Li() {
    Face tmp;

    // Ring rotation
    tmp = cells[4][2];
    cells[4][2] = cells[0][2];
    cells[0][2] = cells[5][2];
    cells[5][2] = cells[2][1];
    cells[2][1] = tmp;

    tmp = cells[4][0];
    cells[4][0] = cells[0][0];
    cells[0][0] = cells[5][0];
    cells[5][0] = cells[2][3];
    cells[2][3] = tmp;

    // Face rotation
    tmp = cells[3][0];
    cells[3][0] = cells[3][1];
    cells[3][1] = cells[3][3];
    cells[3][3] = cells[3][2];
    cells[3][2] = tmp;
  }

  public void R() {
    Face tmp;

    // Ring rotation
    tmp = cells[0][1];
    cells[0][1] = cells[5][1];
    cells[5][1] = cells[2][2];
    cells[2][2] = cells[4][1];
    cells[4][1] = tmp;

    tmp = cells[0][3];
    cells[0][3] = cells[5][3];
    cells[5][3] = cells[2][0];
    cells[2][0] = cells[4][3];
    cells[4][3] = tmp;

    // Face rotation
    tmp = cells[1][0];
    cells[1][0] = cells[1][2];
    cells[1][2] = cells[1][3];
    cells[1][3] = cells[1][1];
    cells[1][1] = tmp;
  }

  public void Ri() {
    Face tmp;

    // Ring rotation
    tmp = cells[4][1];
    cells[4][1] = cells[2][2];
    cells[2][2] = cells[5][1];
    cells[5][1] = cells[0][1];
    cells[0][1] = tmp;

    tmp = cells[4][3];
    cells[4][3] = cells[2][0];
    cells[2][0] = cells[5][3];
    cells[5][3] = cells[0][3];
    cells[0][3] = tmp;

    // Face rotation
    tmp = cells[1][1];
    cells[1][1] = cells[1][3];
    cells[1][3] = cells[1][2];
    cells[1][2] = cells[1][0];
    cells[1][0] = tmp;
  }

  public void U() {
    Face tmp;

    // Ring rotation
    tmp = cells[0][1];
    cells[0][1] = cells[1][1];
    cells[1][1] = cells[2][1];
    cells[2][1] = cells[3][1];
    cells[3][1] = tmp;

    tmp = cells[0][0];
    cells[0][0] = cells[1][0];
    cells[1][0] = cells[2][0];
    cells[2][0] = cells[3][0];
    cells[3][0] = tmp;

    // Face rotation
    tmp = cells[4][0];
    cells[4][0] = cells[4][2];
    cells[4][2] = cells[4][3];
    cells[4][3] = cells[4][1];
    cells[4][1] = tmp;
  }

  public void Ui() {
    Face tmp;

    // Ring rotation
    tmp = cells[3][1];
    cells[3][1] = cells[2][1];
    cells[2][1] = cells[1][1];
    cells[1][1] = cells[0][1];
    cells[0][1] = tmp;

    tmp = cells[3][0];
    cells[3][0] = cells[2][0];
    cells[2][0] = cells[1][0];
    cells[1][0] = cells[0][0];
    cells[0][0] = tmp;

    // Face rotation
    tmp = cells[4][1];
    cells[4][1] = cells[4][3];
    cells[4][3] = cells[4][2];
    cells[4][2] = cells[4][0];
    cells[4][0] = tmp;
  }

  public void D() {
    Face tmp;

    // Ring rotation
    tmp = cells[0][2];
    cells[0][2] = cells[3][2];
    cells[3][2] = cells[2][2];
    cells[2][2] = cells[1][2];
    cells[1][2] = tmp;

    tmp = cells[0][3];
    cells[0][3] = cells[3][3];
    cells[3][3] = cells[2][3];
    cells[2][3] = cells[1][3];
    cells[1][3] = tmp;

    // Face rotation
    tmp = cells[5][0];
    cells[5][0] = cells[5][2];
    cells[5][2] = cells[5][3];
    cells[5][3] = cells[5][1];
    cells[5][1] = tmp;
  }

  public void Di() {
    Face tmp;

    // Ring rotation
    tmp = cells[1][2];
    cells[1][2] = cells[2][2];
    cells[2][2] = cells[3][2];
    cells[3][2] = cells[0][2];
    cells[0][2] = tmp;

    tmp = cells[1][3];
    cells[1][3] = cells[2][3];
    cells[2][3] = cells[3][3];
    cells[3][3] = cells[0][3];
    cells[0][3] = tmp;

    // Face rotation
    tmp = cells[5][1];
    cells[5][1] = cells[5][3];
    cells[5][3] = cells[5][2];
    cells[5][2] = cells[5][0];
    cells[5][0] = tmp;
  }
}
