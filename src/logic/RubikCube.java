package logic;

public class RubikCube {

  // Possible rotations :
  // Front, Back, Left, Right, Up, Down
  // Each rotation has a reverse counterpart

  /** Number of sides the RubikCube has. */
  public static final int NB_SIDES = 6;
  /** Number of cells each side has. */
  public static final int NB_CELLS = 4;

  private Face cells[][];

  public RubikCube() {
    this.cells = new Face[NB_SIDES][NB_CELLS];
    this.init();
  }

  public void init() {
    for (int s = 0; s < NB_SIDES; s++) {
      for (int c = 0; c < NB_CELLS; c++) {
        this.cells[s][c] = Face.getFace(s);
      }
    }
  }

  public Face[] getSide(int side) {
    return this.cells[side];
  }

  public Face getFace(int side, int face) {
    return this.getSide(side)[face];
  }

  private void ringRotation(Face f1, Face f2, Face f3, Face f4, Face f5, Face f6, Face f7,
      Face f8) {

  }

  /**
   * @param f1 -> f2
   * @param f2 -> f3
   * @param f3 -> f4
   * @param f4 -> f1
   */
  /*private void faceRotation(Face f1, Face f2, Face f3, Face f4) {
    Face tmp = f1;

    f1 = f4;
    f4 = ;
    cells[0][3] = cells[0][1];
    cells[0][1] = tmp;
  }*/

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

}
